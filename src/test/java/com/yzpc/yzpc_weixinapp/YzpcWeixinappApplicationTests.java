package com.yzpc.yzpc_weixinapp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class YzpcWeixinappApplicationTests {
    @Resource
    private RabbitTemplate rabbitTemplate;
//    @RabbitListener(queues = "hello.queue1")
//    public void listenSimpleQueueMessage(String msg) throws InterruptedException {
//        System.out.println("hello.queue1接收到消息：【" + msg + "】");
//    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "hello.queue2"),
            exchange = @Exchange(name = "amq.fanout", type = ExchangeTypes.FANOUT)
//            key = {"red", "yellow"}
    ))
    public void listenSimpleQueueMessage2(String msg) throws InterruptedException {
        System.out.println("hello.queue2接收到消息：【" + msg + "】");
    }

//    @RabbitListener(queuesToDeclare = @Queue(
//            name = "lazy.queue",
//            durable = "true",
//            arguments = @Argument(name = "x-queue-mode", value = "lazy")
//    ))
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    name = "lazy.queue",
                    durable = "true",
                    arguments = @Argument(name = "x-queue-mode", value = "lazy")
            ),
            exchange = @Exchange(name = "amq.direct",type = ExchangeTypes.DIRECT),
            key = {"lazy"}
    ))
    public void listenLazyQueue(String msg){
        System.out.println("接收到 lazy.queue的消息："+msg);
    }
    @Test
    public void testSimpleQueue() {
        String exchange="amq.direct";
        // 队列名称
        String queueName = "lazy.queue";
        // 消息
        String message = "hello, spring amqp!";
        Message mes = MessageBuilder.withBody(message.getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .build();
        // 发送消息
        for (int i = 0; i < 10000; i++) {
            rabbitTemplate.convertAndSend(exchange,"lazy",mes);
        }
    }

    @Test
    public void testSimpleFanout() {
        // 队列名称
        String fanoutName = "amq.fanout";
        // 消息
        String message = "hello,everyone!";
        // 发送消息
        rabbitTemplate.convertAndSend(fanoutName,null, message);
    }

    @Test
    void contextLoads() {
    }



}
