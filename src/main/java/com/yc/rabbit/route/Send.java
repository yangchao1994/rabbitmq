package com.yc.rabbit.route;

import com.rabbitmq.client.BuiltinExchangeType;
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
        String type = BuiltinExchangeType.DIRECT.getType();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");//fenout 和 direct 时rabbit指定的枚举类型这里直接写了字符串

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes());
        System.out.println(message);

        channel.close();
        connection.close();
    }
}
