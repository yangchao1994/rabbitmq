package com.yc.rabbit.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yc.rabbit.util.ConnectionUtil;

public class Send {
    //有交换机以后先启动生产者创建交换机不然消费者绑定交换机会报错
    private final static String EXCHANGE_NAME = "test_topic";
    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        String type = BuiltinExchangeType.TOPIC.getType();
        channel.exchangeDeclare(EXCHANGE_NAME, type);//指明交换机类型类主题模式

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "computer.update", null, message.getBytes());
        System.out.println(message);

        channel.close();
        connection.close();
    }
}
