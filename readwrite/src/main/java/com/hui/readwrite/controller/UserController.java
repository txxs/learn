package com.hui.readwrite.controller;

import com.hui.readwrite.po.Count;
import com.hui.readwrite.po.User;
import com.hui.readwrite.service.CountService;
import com.hui.readwrite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/28
 * @Time:11:19
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/users",method = {RequestMethod.GET})
    public List<User> getUserList(){
        return userService.getUserList();
    }

    @RequestMapping(value = "path=/user/id",method = {RequestMethod.PUT})
    public Integer updateUser(@RequestBody User user){
        return userService.updateOneUser(user.getId());
    }
}
