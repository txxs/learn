package com.hui.readwrite.respository;

import com.hui.readwrite.mapper.master2.CountMapper;
import com.hui.readwrite.po.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/28
 * @Time:11:25
 */
@Repository
public class CountRespository {

    @Autowired
    private CountMapper countMapper;

    public List<Count> getCountList(){
        return countMapper.getCountList();
    }

    public Integer updateOneCount(Integer id){
        return countMapper.updateOneCount(id);
    }
}
