package com.yc.rabbit.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yc.rabbit.util.ConnectionUtil;

public class Send {
    //有交换机以后先启动生产者创建交换机不然消费者绑定交换机会报错
    private final static String EXCHANGE_NAME = "test_exchange";
    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(message);

        channel.close();
        connection.close();
    }
}
