package com.rabbit.sender;

import java.util.concurrent.TimeUnit;

import com.rabbit.config.RabbitConfig;
import com.rabbit.receiver.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
/**
 * @Author:jianghuimin
 * @Date: 2017/5/15
 * @Time:15:27
 */

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate,
                  ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitConfig.logsQueueNameOne, "One Hello from RabbitMQ!");
        rabbitTemplate.convertAndSend(RabbitConfig.logsQueueNameTwo, "Two Hello from RabbitMQ!");
        rabbitTemplate.convertAndSend(RabbitConfig.orderQueueName, "order Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
    }

}