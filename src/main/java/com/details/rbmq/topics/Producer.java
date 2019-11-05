package com.details.rbmq.topics;

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
 * topic模式，就是在路由的基础上更加开放的对路由id进行匹配。
 *
 * @author zlp
 * @date 2019-11-04 14:23
 */
public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    private static final String QUEUE_INFORM_TOPIC_SMS = "queue_inform_topic_sms";
    private static final String QUEUE_INFORM_TOPIC_EMAIL = "queue_inform_topic_email";

    private static final String EXCHANGE_TOPIC_INFORM="exchange_topic_inform";

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
            channel.queueDeclare(QUEUE_INFORM_TOPIC_SMS, true, false, false, null);
            channel.queueDeclare(QUEUE_INFORM_TOPIC_EMAIL, true, false, false, null);
            //声明交换机  交换机类型 fanout、topic、direct、headers
            channel.exchangeDeclare(EXCHANGE_TOPIC_INFORM, BuiltinExchangeType.TOPIC);
            //交换机与队列绑定  topic由消费端设置绑定参数
//            channel.queueBind(EXCHANGE_TOPIC_INFORM,QUEUE_INFORM_TOPIC_EMAIL,QUEUE_INFORM_TOPIC_EMAIL);
//            channel.queueBind(EXCHANGE_TOPIC_INFORM,QUEUE_INFORM_TOPIC_SMS,QUEUE_INFORM_TOPIC_SMS);
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
            //邮件
            for (int i = 0; i < 100; i++) {
                Random random = new Random();
                random.nextInt();
                String message = "email  步步含坑 UUID   "+UUID.randomUUID().toString();
                channel.basicPublish(EXCHANGE_TOPIC_INFORM,"inform.email", null, message.getBytes());
                logger.info("/-/-/-/-/-/-/-/-/-/-/-生产消息内容为："+message);
            }
            //短信
            for (int i = 0; i < 100; i++) {
                Random random = new Random();
                random.nextInt();
                String message = "sms  步步含坑 UUID   "+UUID.randomUUID().toString();
                channel.basicPublish(EXCHANGE_TOPIC_INFORM,"inform.sms", null, message.getBytes());
                logger.info("/-/-/-/-/-/-/-/-/-/-/-生产消息内容为："+message);
            }
            //all
            for (int i = 0; i < 100; i++) {
                Random random = new Random();
                random.nextInt();
                String message = "all  步步含坑 UUID   "+UUID.randomUUID().toString();
                channel.basicPublish(EXCHANGE_TOPIC_INFORM,"inform.sms.email", null, message.getBytes());
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
