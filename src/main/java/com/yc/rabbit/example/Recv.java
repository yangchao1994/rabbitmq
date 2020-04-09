package com.yc.rabbit.example;

import com.rabbitmq.client.*;
import com.yc.rabbit.util.ConnectionUtil;

import java.io.IOException;

public class Recv {

    private final static String QUEUE_NAME = "test";

    public static void main(String[] argv) throws Exception {
        //oldApi();
        newApi();
    }

    private static void newApi() throws Exception{
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //java覆写方法的一种写法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //一旦有消息进入队列就会触发该方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body, "UTF-8"));
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }

    public static void oldApi() throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(message);
        }
    }
}
