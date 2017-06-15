package learn.memcached.kafka.seniver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import learn.memcached.kafka.po.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/14
 * @Time:11:49
 */
@Component
public class Sender {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    private Gson gson = new GsonBuilder().create();

    public void sendMessage(){
        Message m = new Message();
        m.setId(System.currentTimeMillis());
        m.setMsg(UUID.randomUUID().toString());
        m.setSendTime(new Date());
        kafkaTemplate.send("test1", gson.toJson(m));
        System.out.println("发送消息"+m.toString());
    }
}
