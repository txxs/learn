package com.zookeeper.distributeLock;

import com.google.common.base.Strings;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/24
 * @Time:20:40
 */
public class DistributeLock {

    private  String lockZnode = null;

    private static ZooKeeper zk;

    private static String zooKeeperUrl="localhost:2181,localhost:2182,localhost:2183";

    static {
        CountDownLatch connectorSemaphore = new CountDownLatch(1);
        String url = zooKeeperUrl;
        int timeout = 60000;
        try {
            zk = new ZooKeeper(url, timeout, new ConnectWatcher(connectorSemaphore));
            System.out.println("++++++++++++++++++"+zk.getState());
            connectorSemaphore.await();
            System.out.println("++++++++++++++++++"+zk.getState());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取锁
     * @return
     * @throws InterruptedException
     */
    public void lock(){
        try {
            Stat stat = zk.exists("/locknode", false);//此去不执行 Watcher
            if(stat == null){
                //创建根节点，永久存在
                zk.create("/locknode", "lock".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            String path = zk.create("/locknode/guid-lock","lock".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            lockZnode=path;
            List<String> children = zk.getChildren("/locknode", true);
            Collections.sort(children);
            if (!Strings.nullToEmpty(path).trim().isEmpty()
                    &&!Strings.nullToEmpty(children.get(0)).trim().isEmpty()
                    &&path.equals("/locknode/"+children.get(0))) {
                System.out.println(Thread.currentThread().getName() + "  get Lock...");
                return;
            }
            String watchNode = null;
            for (int i=children.size()-1;i>=0;i--){
                if(children.get(i).compareTo(path.substring(path.lastIndexOf("/") + 1))<0){
                    watchNode = children.get(i);
                    break;
                }
            }

            if (watchNode!=null){
                final String watchNodeTmp = watchNode;
                //给当前线程的创建的znode小的znode添加监听，当发生删除事件的时候只叫醒当前的这个线程
                final Thread thread = Thread.currentThread();
                Stat stat1 = zk.exists("/locknode/"  + watchNodeTmp,new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        if(watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted){
                            thread.interrupt();
                        }
                        try {
                            zk.exists("/locknode/" + watchNodeTmp,true);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                });
                if(stat1 != null){
                    System.out.println("Thread " + Thread.currentThread().getId() + " waiting for " + "/locknode/" + watchNode);
                }
            }
            try {
                //等待直到被唤醒
                Thread.sleep(1000000000);
            }catch (InterruptedException ex){
                System.out.println(Thread.currentThread().getName() + " notify");
                System.out.println(Thread.currentThread().getName() + "  get Lock...");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放锁
     */
    public void unlock(){
        try {
            System.out.println(Thread.currentThread().getName() +  "release Lock...");
            zk.delete(lockZnode,-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

}
