package learn.memcached.kafka.retry.sendMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/13
 * @Time:17:21
 */
@RestController
public class SendController {

    @Autowired
    private SendService sendService;

    @RequestMapping("/sendMsg")
    public void sendMsg(){
        sendService.sendMsg();
    }
}
