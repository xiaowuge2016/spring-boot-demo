package com.lifujian.demo.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author lifujian
 * @date  2018/08/26 18:19
 * @description 消费者
 */
@Component
public class Consumer {

    @JmsListener(destination = "sample.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer :::: " + text );
    }
}
 