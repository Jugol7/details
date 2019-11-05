package com.details.rbmq.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 生产者
 *
 * @author zlp
 * @date 2019-11-01 14:23
 */
public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    private static final String QUEUE_FIRST = "first";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        logger.info("/-/-/-/-/-/-/-/-/-/-/-开始设置连接信息");
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
            //2.创建通道
            channel = connection.createChannel();
            //3.创建队列
            /**
             * Queue.DeclareOk queueDeclare(
             * String queue 队列,
             * boolean durable 是否持久化,
             * boolean exclusive 是否独立，独自占用一个来连接,
             * boolean autoDelete 队列不使用时，是否自动删除,
             * ap<String, Object> arguments 其他队列参数
             * ) throws IOException;
             */
            logger.info("/-/-/-/-/-/-/-/-/-/-/-开始设置连接信息");
            channel.queueDeclare(QUEUE_FIRST, true, false, false, null);
            //4.发送消息
            /**
             * channel.basicPublish(
             * String exchange,   不指定使用默认交换机
             * String routingKey,   消息的路由key，交换机可通过指定发送到的队列
             * BasicProperties props,   消息的属性
             * byte[] body   消息的内容
             * ) throws IOException;
             * * 这里没有指定交换机，消息将发送给默认交换机，每个队列也会绑定那个默认的交换机，但是不能显示绑定或解除绑定
             *  默认的交换机，routingKey等于队列名称
             */
            for (int i = 0; i < 100; i++) {
                Random random = new Random();
                random.nextInt();
                String message = "步步含坑"+i;
                channel.basicPublish("", QUEUE_FIRST, null, message.getBytes());
                logger.info("/-/-/-/-/-/-/-/-/-/-/-生产消息内容为："+message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            close(channel, connection);
        }
    }

    public static void close(Channel channel, Connection connection){
        if(channel!=null){
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
