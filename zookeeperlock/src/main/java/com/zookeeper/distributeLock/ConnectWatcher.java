package com.zookeeper.distributeLock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/23
 * @Time:10:36
 */
public class ConnectWatcher implements Watcher {

    private final CountDownLatch connectorSemaphore;

    public ConnectWatcher(CountDownLatch connectorSemaphore){
        this.connectorSemaphore = connectorSemaphore;
    }
    @Override
    public void process(WatchedEvent e) {
        if (e.getState() == Event.KeeperState.SyncConnected){
            if (Event.EventType.None == e.getType() && e.getPath() == null){
                connectorSemaphore.countDown();
                System.out.println("链接状态：" + e.getState());
            }
        }
    }

}
