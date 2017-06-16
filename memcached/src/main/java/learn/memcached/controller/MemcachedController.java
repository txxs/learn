package learn.memcached.controller;

import learn.memcached.service.MemcachedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/15
 * @Time:10:59
 */
@RestController
public class MemcachedController {

    @Autowired
    private MemcachedService memcachedService;

    @RequestMapping("/testMem")
    public String testMem(){
        return  memcachedService.getMem("key1");
    }
}
