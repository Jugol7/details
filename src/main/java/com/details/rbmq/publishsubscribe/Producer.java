package com.details.rbmq.publishsubscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 生产者
 * 发布订阅模式 ； 指定交换机，并且与队列绑定
 * 由交换机将消息转发到绑定此交换机的每个队列，每个绑定交换机的队列都将接收到消息
 * 与工作模式的区别在于；是否使用自定义的交换机
 *               相同点；两者发布/订阅效果是一样的，多个消费者监听同一个队列不会重复消费消息
 *
 *
 * 极高的并发性能：可在消费者中开启多线程，最常用的做法是一个channel对应一个消费者，每一个线程把持一个channel，多个线程复用connection的tcp连接，减少性能开销。
 * 当rabbitmq队列拥有多个消费者的时候，队列收到的消息将以轮询的分发方式发送给消费者。每条消息只会发送给订阅列表里的一个消费者，不会重复。
 * 这种方式非常适合扩展，而且是专门为并发程序设计的。
 * 如果某些消费者的任务比较繁重，那么可以设置basicQos限制信道上消费者能保持的最大未确认消息的数量，在达到上限时，rabbitmq不再向这个消费者发送任何消息。
 *
 * @author zlp
 * @date 2019-11-04 14:23
 */
public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";

    private static final String EXCHANGE_FANOUT_INFORM="exchange_fanout_inform";

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
            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);
            channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, null);
            //声明交换机
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
            //交换机与队列绑定
            channel.queueBind(QUEUE_INFORM_EMAIL,EXCHANGE_FANOUT_INFORM,QUEUE_INFORM_EMAIL);
            channel.queueBind(QUEUE_INFORM_SMS,EXCHANGE_FANOUT_INFORM,QUEUE_INFORM_SMS);
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
                String message = "步步含坑 UUID   "+UUID.randomUUID().toString();
                channel.basicPublish(EXCHANGE_FANOUT_INFORM,"", null, message.getBytes());
                logger.info("/-/-/-/-/-/-/-/-/-/-/-生产消息内容为："+message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            com.details.rbmq.workqueues.Producer.close(channel, connection);
        }
    }

}
