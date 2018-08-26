package com.lifujian.demo.activemqtest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import com.lifujian.demo.activemq.Producer;

/**
 * @author lifujian
 * @date  2018/08/26 18:21
 * @description activemq 测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleActiveMqTests {

    // 用于扩展JUnit的功能，灵活地改变测试方法的行为。需要放在实现了TestRule借口的成员变量（@Rule）或者静态变量（@ClassRule）上。@Rule是方法级别的。
    // 使用 OutputCapture 来捕获指定方法开始执行以后的所有输出，包括System.out输出和Log日志。 
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Autowired
    private Producer producer;

    @Test
    public void sendSimpleMessage() throws InterruptedException {
        this.producer.send("hello, 世界！");
        Thread.sleep(1000L);
        assertThat(this.outputCapture.toString().contains("hello, 世界！")).isTrue();
    }
    
}
 