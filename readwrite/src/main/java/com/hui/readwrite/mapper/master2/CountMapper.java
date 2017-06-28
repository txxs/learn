package com.hui.readwrite.mapper.master2;

import com.hui.readwrite.po.Count;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/28
 * @Time:11:23
 */
@Mapper
public interface CountMapper {

    List<Count> getCountList();

    Integer updateOneCount(Integer id);
}
