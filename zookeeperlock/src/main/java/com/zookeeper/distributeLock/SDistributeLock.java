package com.zookeeper.distributeLock;

import com.google.common.base.Strings;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/25
 * @Time:20:49
 */
public class SDistributeLock {

    private  String lockZnode = null;

    private String lockNameSpace = "/mylock";

    private String nodeString = lockNameSpace + "/test1";

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

    private void ensureRootPath() throws InterruptedException {
        try {
            if (zk.exists(lockNameSpace,true)==null){
                zk.create(lockNameSpace,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private void watchNode(final String nodeString, final Thread thread) throws InterruptedException {
        try {
            zk.exists(nodeString, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("out  "+thread.getName()+"==" + watchedEvent.toString());
                    if(watchedEvent.getType() == Event.EventType.NodeDeleted){
                        System.out.println("外部  "+thread.getName()+"==" + "进入中断");
                        thread.interrupt();
                    }
                    try {
                        zk.exists(nodeString,new Watcher() {
                            @Override
                            public void process(WatchedEvent watchedEvent) {
                                System.out.println("in  "+thread.getName()+ "==" + watchedEvent.toString());
                                if(watchedEvent.getType() == Event.EventType.NodeDeleted){
                                    System.out.println("内部  "+thread.getName()+"==" + "进入中断");
                                    thread.interrupt();
                                }
                                try {
                                    zk.exists(nodeString,true);
                                } catch (KeeperException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取锁
     * @return
     * @throws InterruptedException
     */
    public boolean lock() throws InterruptedException {
        String path = null;
        ensureRootPath();
        watchNode(nodeString,Thread.currentThread());
        while (true) {
            try {
                path = zk.create(nodeString, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            } catch (KeeperException e) {
                System.out.println(Thread.currentThread().getName() + "  getting Lock but can not get");
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException ex){
                    System.out.println("thread is notify");
                }
            }
            if (!Strings.nullToEmpty(path).trim().isEmpty()) {
                System.out.println(Thread.currentThread().getName() + "  get Lock...");
                return true;
            }
        }
    }

    /**
     * 释放锁
     */
    public void unlock(){
        try {
            zk.delete(nodeString,-1);
            System.out.println(Thread.currentThread().getName() + " release Lock...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

}
