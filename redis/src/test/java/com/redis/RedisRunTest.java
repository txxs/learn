package com.redis;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.po.User;
import com.redis.service.TestCacheService;
import com.redis.utils.SerializeUtil;
import com.redis.vo.UserQuery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/16
 * @Time:10:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisRunTest {

    @Autowired
    private TestCacheService testCacheService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存取字符串
     * @throws Exception
     */
    @Test
    public void testString() throws Exception {
        redisTemplate.opsForValue().set("aaa1", "111");
        Assert.assertEquals("111", redisTemplate.opsForValue().get("aaa1"));
    }

    /**
     * 存取List
     * @throws Exception
     */
    @Test
    public void testList() throws Exception {
        List<String> testList = new LinkedList<String>();
        testList.add("listOne");
        testList.add("listTwo");
        testList.add("listThree");
        redisTemplate.opsForValue().set("testList",testList);
        String testList1 =  redisTemplate.opsForValue().get("testList").toString();
        System.out.print(testList1);
    }

    /**
     * 存取Map
     * @throws Exception
     */
    @Test
    public void testMap() throws Exception {
        Map<String,String> tesMap = new HashMap<String, String>();
        tesMap.put("key1","value1");
        tesMap.put("key2","value2");
        tesMap.put("key3","value3");
        redisTemplate.opsForValue().multiSet(tesMap);
        redisTemplate.opsForValue().set("testMap",tesMap);
        String testMap1 =  redisTemplate.opsForValue().get("testMap").toString();
        System.out.print(testMap1);
    }

    /**
     * 存取自定义序列化对象
     * @throws Exception
     */
    @Test
    public void testListObject() throws Exception {
        List<User> testListObject = new LinkedList<User>();
        User userOne = new User();
        userOne.setId(0);
        userOne.setAddressName("changanjie");
        userOne.setName("jianghuimin");
        List<String> phoneNum = new LinkedList<String>();
        phoneNum.add("15810568887");
        phoneNum.add("15810568888");
        phoneNum.add("15810568889");
        userOne.setPhoneNum(phoneNum);
        User userTwo = new User();
        userTwo.setId(1);
        userTwo.setAddressName("changanjie");
        userTwo.setName("jianghuimin");
        List<String> phoneNumTwo = new LinkedList<String>();
        phoneNumTwo.add("15810568897");
        phoneNumTwo.add("15810568898");
        phoneNumTwo.add("15810568899");
        userTwo.setPhoneNum(phoneNumTwo);
        testListObject.add(userOne);
        testListObject.add(userTwo);
        redisTemplate.opsForValue().set("testObject", userTwo);
        redisTemplate.opsForValue().set("testObjectList", testListObject);
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = objectMapper.writeValueAsString(redisTemplate.opsForValue().get("testObject"));
        System.out.print(object.toString());
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        Object lst = objectMapper.writeValueAsString(redisTemplate.opsForValue().get("testObjectList"));
        System.out.print(lst.toString());
    }

    /**
     * 测试缓存,参数个数来定
     */
    @Test
    public void testCacheNum(){
        testCacheService.findUser(1l,"2","2");
    }

    /**
     * 测试缓存,参数是一个对象
     */
    @Test
    public void testCacheObject(){
        testCacheService.findUserByObject(new UserQuery(2,"name","addressNmae"));
    }
}
