package com.zookeeper.distributeLock;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/23
 * @Time:10:36
 */
public class LockWatcher implements Watcher {
    private ZooKeeper zk;
    private final String path;
    public LockWatcher(String path){
        this.path = path;
    }
    public void process(WatchedEvent event) {
        try {
            Stat stat = zk.exists(path, false);
            System.out.println("当前线程获取到分布式锁" + stat);
        } catch (KeeperException e) {            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
