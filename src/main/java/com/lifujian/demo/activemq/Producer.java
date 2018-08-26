package com.lifujian.demo.activemq;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lifujian
 * @date  2018/08/26 18:18
 * @description 生产者
 */
@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, "Producer 在" + System.currentTimeMillis() + "时发送消息：" + msg);
    }
}
 