package com.rabbit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/12
 * @Time:10:34
 */
@RestController
public class Controller {

    @RequestMapping("/ee")
    public String esd() {
        return "hhh";
    }
}
