package learn.memcached.service;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String getMem(String key){
        if(memcachedClient.get(key)==null) {
            System.out.println("缓存中不存在，在数据库中获取……");
            System.out.println("模拟在数据库中获取……");
            //若数据中返回的数据为null，则返回，不为空则先存缓存，再返回
            if(true){
                memcachedClient.set(key,0,key+"value");
                return key+"value";
            }
        }else{
            return memcachedClient.get(key).toString();
        }
        return null;
    }

    /**
     * 增加数据
     * @param key
     */
    public void addMem(String key){
        //先存放数据库，再存memcache
        System.out.println("模拟存入数据库");
        memcachedClient.set(key,0,key+"value");
    }

    /**
     * 修改数据
     * @param key
     */
    public void modifyMem(String key){
        //先修改新值，存入数据库，在获取，然后存入缓存
        memcachedClient.flush();
    }

    /**
     *删除数据
     * @param key
     */
    public void deleteMem(String key){
        //删除数据库，再删除缓存
        memcachedClient.flush();
    }

}
