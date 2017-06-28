package com.hui.readwrite.service;

import com.hui.readwrite.po.User;
import com.hui.readwrite.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/28
 * @Time:11:32
 */
@Service
public class UserService {

    @Autowired
    private UserRespository userRespository;

    public Integer updateOneUser(Integer id){
        return  userRespository.updateOneUser(id);
    }

    public List<User> getUserList(){
       return userRespository.getUserList();
    }
}
