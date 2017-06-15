package com.redis.service;

import com.redis.po.User;
import com.redis.vo.UserQuery;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/17
 * @Time:14:59
 */
@Service
public class TestCacheService {

    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    public User findUser(Long id, String firstName, String lastName){
        System.out.println("NUM无缓存的时候调用这里");
        return new User(1,"name","adddressName");
    }

    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    public User findUserByObject(UserQuery userQuery){
        System.out.println("BJECT无缓存的时候调用这里");
        return new User(1,"name","adddressName");
    }
}
