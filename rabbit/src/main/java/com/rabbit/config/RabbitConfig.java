package com.rabbit.config;

import com.rabbit.receiver.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/12
 * @Time:10:14
 */
@Configuration
public class RabbitConfig {

    public final static String logsQueueNameOne = "logsOne";
    public final static String logsQueueNameTwo = "logsTwo";
    public final static String orderQueueName = "order";

    @Bean
    Queue logsQueueOne() {
        return new Queue(logsQueueNameOne, false);
    }

    @Bean
    Queue logsQueueTwo() {
        return new Queue(logsQueueNameTwo, false);
    }

    @Bean
    Queue orderQueue() {
        return new Queue(orderQueueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("logsExchange");
    }

    @Bean
    TopicExchange orderExchange() {
        return new TopicExchange("logsExchange");
    }

    @Bean
    Binding binding(Queue logsQueueOne, TopicExchange exchange) {
        return BindingBuilder.bind(logsQueueOne).to(exchange).with(logsQueueNameOne);
    }

    @Bean
    Binding bindin(Queue logsQueueTwo, TopicExchange exchange) {
        return BindingBuilder.bind(logsQueueTwo).to(exchange).with(logsQueueNameTwo);
    }

    @Bean
    Binding bindi(Queue orderQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(orderQueueName);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(logsQueueNameOne,logsQueueNameTwo,orderQueueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
