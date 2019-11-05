package com.details.rbmq.header;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
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

    private static final String QUEUE_INFORM_HEADER_SMS = "queue_inform_header_sms";
    private static final String QUEUE_INFORM_HEADER_EMAIL = "queue_inform_header_email";
    private static final String EXCHANGE_HEADER_INFORM = "exchange_header_inform";

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
            channel.queueDeclare(QUEUE_INFORM_HEADER_SMS, true, false, false, null);
            channel.queueDeclare(QUEUE_INFORM_HEADER_EMAIL, true, false, false, null);
            //声明交换机  交换机类型 fanout、topic、direct、headers
            channel.exchangeDeclare(EXCHANGE_HEADER_INFORM, BuiltinExchangeType.HEADERS);
            //交换机与队列绑定  topic由消费端设置绑定参数
            Map<String, Object> mapEmail = new Hashtable<>();
            mapEmail.put("type", "email");
            Map<String, Object> mapSms = new Hashtable<>();
            mapSms.put("type", "sms");
            channel.queueBind(QUEUE_INFORM_HEADER_EMAIL,EXCHANGE_HEADER_INFORM, "", mapEmail);
            channel.queueBind(QUEUE_INFORM_HEADER_SMS, EXCHANGE_HEADER_INFORM,"", mapSms);
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
                String message = "email  步步含坑 UUID   " + UUID.randomUUID().toString();
                Map<String, Object> headers = new Hashtable<>();
                //匹配email通知消费者绑定的header
                headers.put("type", "email");
                AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
                properties.headers(headers);
                channel.basicPublish(EXCHANGE_HEADER_INFORM, "", properties.build(), message.getBytes());
                logger.info("/-/-/-/-/-/-/-/-/-/-/-生产消息内容为：" + message);
            }
            for (int i = 0; i < 100; i++) {
                Random random = new Random();
                random.nextInt();
                String message = "sms  步步含坑 UUID   " + UUID.randomUUID().toString();
                Map<String, Object> headers = new Hashtable<>();
                //匹配sms通知消费者绑定的header
                headers.put("type", "sms");
                AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
                properties.headers(headers);
                channel.basicPublish(EXCHANGE_HEADER_INFORM, "", properties.build(), message.getBytes());
                logger.info("/-/-/-/-/-/-/-/-/-/-/-生产消息内容为：" + message);
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
