package com.hui.readwrite.mapper.master1;

import com.hui.readwrite.po.Count;
import com.hui.readwrite.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/28
 * @Time:11:23
 */
@Mapper
public interface UserMapper {

    List<User> getUserList();

    Integer updateOneUser(Integer id);
}
