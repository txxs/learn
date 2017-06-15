package learn.memcached.kafka.seniver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import learn.memcached.kafka.po.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/14
 * @Time:11:53
 */
@Component
public class Receiver {

    private Gson gson = new GsonBuilder().create();

    @KafkaListener(topics = "test1")
    public void processMessage(String content) {
        Message m = gson.fromJson(content, Message.class);
        System.out.println("接收到消息"+m.toString());
    }
}
