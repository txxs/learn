package learn.memcached.service;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/15
 * @Time:11:10
 */
@Service
public class MemcachedService {

    @Autowired
    private MemcachedClient memcachedClient;

    public String testMem(String key){
        if(memcachedClient.get(key)==null){
            System.out.println("未经过缓存");
            memcachedClient.set("key",30,"jingguohuancn");
        }else {
            return memcachedClient.get(key).toString();
        }
        return null;
    }
}
