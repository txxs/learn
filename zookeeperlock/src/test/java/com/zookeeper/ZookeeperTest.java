package com.zookeeper;

import com.zookeeper.distributeLock.LockWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/23
 * @Time:10:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ZookeeperTest {

    @Autowired
    private ZooKeeper zk;

    @Test
    public void getDistributedLock(){
        try {
            //创建一个节点root，数据是mydata,不进行ACL权限控制，节点为永久性的(即客户端shutdown了也不会消失)

            String path1 = zk.create("/locknode", "lock".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            String path0 = zk.create("/locknode/guid-lock","lock".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
            System.out.println(path0);
            List<String> children = zk.getChildren("/locknode", true);
            for(String ch:children){
                System.out.println(ch);
            }
            if (children != null && children.size() > 0){
                // 避免羊群效应
                // 首先找到比自己小的那个节点
                int size = children.size();
                String prevOne = null;// 上一个比自己小的节点
                for (int i=0;i<size;i++){
                    if (i == 0 && path0.lastIndexOf(children.get(i)) > 0){
                        System.out.println("当前线程获取分布式锁。。。");
                        return;
                    }
                    if (path0.lastIndexOf(children.get(i)) > 0){
                        prevOne = children.get(i-1);
                        break;
                    }
                }
                System.out.println("prevOne:" + prevOne);
                // 判断比当前节点小的节点是否存在，其节点发生变化之后会回调watcher类，即当前线程获取到分布式锁
                zk.exists("/locknode/" + prevOne, new LockWatcher("/locknode"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
