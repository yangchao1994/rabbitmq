package com.yc.rabbit.work能者多劳;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yc.rabbit.util.ConnectionUtil;

public class Send {

    private final static String QUEUE_NAME = "test_workqueue";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        boolean durable = false;//消息持久化
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        //能者多劳模式前提
        //设置生产者再没有得到消费者完成消息处理的响应前指定只给消费者发一个消息
        //设置消费者关闭自动应答并且手动响应
        channel.basicQos(1);
        for(int i=1;i<=10;i++){
            // 消息内容
            String message = "Hello World!=========="+i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        }

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
