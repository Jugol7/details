package com.details.rbmq.publishsubscribe;

import com.details.rbmq.workqueues.Producer;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @author zlp
 * @date 2019-11-04 14:44
 */
@Slf4j
public class ConsumerSms {

    private static Logger logger = LoggerFactory.getLogger(ConsumerSms.class);

    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_FANOUT_INFORM="exchange_fanout_inform";

    public static void main(String [] a) {
        Connection connection = null;
        Channel channel = null;
        logger.info("/-/-/-/-/-/-/-/-/-/-/-开始设置连接信息");
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        try {
            //1.创建连接
            connection = connectionFactory.newConnection();
            //2.exchange channel
            channel = connection.createChannel();
            //3.声明队列
            channel.queueDeclare(QUEUE_INFORM_SMS,true,false,false,null);
            channel.basicQos(1);
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM,BuiltinExchangeType.FANOUT);
            channel.queueBind(QUEUE_INFORM_SMS,EXCHANGE_FANOUT_INFORM,QUEUE_INFORM_SMS);
            //4.声明消费者  重写handleDelivery()
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
                /**
                 * 消费者消费的消息调用此方法
                 * @param consumerTag  消费者的标签  在channel.basicConsume()声明
                 * @param envelope  消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志 收到消息失败后是否需要重新发送)
                 * @param properties  附带属性
                 * @param body 消息内容
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    //交换机
                    String exchange = envelope.getExchange();
                    //rountingkey
                    String routingKey = envelope.getRoutingKey();
                    //消息id  DeliveryId
                    long deliveryTag = envelope.getDeliveryTag();
                    //消息内容 body
                    String s = new String(body, "UTF-8");
                    logger.info("/-/-/-/-/ConsumerSms-/-/-/-/-/-/-消息详情为："+s);
                }
            };
            //3.消费
            /** channel.basicConsume(
             * String queue, 监控的队列
             * boolean autoAck, 是否自动回复
             * ConsumerEmail callback 消费消息调用的方法
             * ) throws IOException
             */
            logger.info("/-/-/-/-/-/-/-/-/-/-/-开始消费消息");
            channel.basicConsume(QUEUE_INFORM_SMS, true, defaultConsumer);
            logger.info("/-/-/-/-/-/-/-/-/-/-/-消息消费结束");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Producer.close(channel,connection);
        }

    }

}
