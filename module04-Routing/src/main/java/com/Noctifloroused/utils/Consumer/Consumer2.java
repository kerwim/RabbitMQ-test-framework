package com.Noctifloroused.utils.Consumer;

import com.Noctifloroused.utils.ConnectionUtil;
import com.rabbitmq.client.*;
import java.io.IOException;  
  
public class Consumer2 {  
  
    public static void main(String[] args) throws Exception {  
  
        Connection connection = ConnectionUtil.getConnection();
  
        Channel channel = connection.createChannel();  
  
        String queue2Name = "test_direct_queue2";  

        /**
         * queue2Name ：队列名
         * 参数2 持久化
         * 参数3 排他性
         * 参数4 自动删除
         * 参数5 其他参数
         */
        channel.queueDeclare(queue2Name,true,false,false,null);  
  
        Consumer consumer = new DefaultConsumer(channel){  
  
            @Override  
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {  
  
                System.out.println("body："+new String(body));  
                System.out.println("Consumer2 将日志信息存储到数据库.....");  
  
            }  
  
        };  
  
        channel.basicConsume(queue2Name,true,consumer);  
  
    }  
  
}