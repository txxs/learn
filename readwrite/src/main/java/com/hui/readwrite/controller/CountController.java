package com.hui.readwrite.controller;

import com.hui.readwrite.po.Count;
import com.hui.readwrite.service.CountService;
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
public class CountController {

    @Autowired
    private CountService countService;

    @RequestMapping(path = "/counts",method = {RequestMethod.GET})
    public List<Count> getCountList(){
        return countService.getCountList();
    }

    @RequestMapping(value = "path=/count/id",method = {RequestMethod.PUT})
    public Integer updateCount(@RequestBody Count count){
        return countService.updateOneCount(count.getId());
    }
}
