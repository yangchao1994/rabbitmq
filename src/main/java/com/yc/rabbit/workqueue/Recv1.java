package com.yc.rabbit.workqueue;

import com.rabbitmq.client.*;
import com.yc.rabbit.util.ConnectionUtil;

import java.io.IOException;

public class Recv1 {
    private final static String QUEUE_NAME = "test_workqueue";
    public static void main(String[] args) throws Exception {
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
                //模拟实际消费者获取消息处理业务耗费不同时间
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }
}
