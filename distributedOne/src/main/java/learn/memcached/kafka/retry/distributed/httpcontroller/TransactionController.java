package learn.memcached.kafka.retry.distributed.httpcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/13
 * @Time:10:09
 */
@RestController
public class TransactionController {

    @RequestMapping("/updateCount")
    public ResponseBody manyTransaction(){

        return null;
    }
}
