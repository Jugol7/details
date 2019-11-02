package com.details.rbmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 生产者
 * @author zlp
 * @date 2019-11-01 14:23
 */
public class Producer {

    private static final String QUEUE_FIRST = "first";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //rabbitmq默认虚拟机名称为“/”。相当与一个独立的服务
        connectionFactory.setVirtualHost("/");
        //1.创建TCP连接
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            //Queue.DeclareOk queueDeclare(String queue 队列, boolean durable 是否持久化, boolean exclusive 是否独立，独自占用一个来连接, boolean autoDelete 队列不使用时，是否自动删除, Map<String, Object> arguments 其他队列参数) throws IOException;
            channel.queueDeclare(QUEUE_FIRST,true,false,false,null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        //2.创建通道

        //3.创建队列

        //4.生产者发送消息

    }
}
