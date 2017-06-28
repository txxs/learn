package com.hui.readwrite.service;

import com.hui.readwrite.po.Count;
import com.hui.readwrite.respository.CountRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/28
 * @Time:11:32
 */
@Service
public class CountService {

    @Autowired
    private CountRespository countRespository;

    public Integer updateOneCount(Integer id){
        return  countRespository.updateOneCount(id);
    }

    public List<Count> getCountList(){
       return countRespository.getCountList();
    }
}
