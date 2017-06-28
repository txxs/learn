package com.hui.readwrite.respository;

import com.hui.readwrite.mapper.master1.UserMapper;
import com.hui.readwrite.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/28
 * @Time:11:25
 */
@Repository
public class UserRespository {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList(){
        return userMapper.getUserList();
    }

    public Integer updateOneUser(Integer id){
        return userMapper.updateOneUser(id);
    }
}
