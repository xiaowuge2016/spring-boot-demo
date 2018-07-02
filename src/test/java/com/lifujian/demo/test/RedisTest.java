package com.lifujian.demo.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.lifujian.demo.model.User;

/**
 * @author lifujian
 * @date  2018/07/02 16:49
 * @description 测试 redis 存储字符串和存储 java对象
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Test
    public void t01_operaRedis() throws Exception {
        stringRedisTemplate.opsForValue().set("name", "李五哥", 400, TimeUnit.SECONDS);
        String name = stringRedisTemplate.opsForValue().get("name");
        System.err.println("=============name:" + name);
        
    }
    
    @Test
    public void t02_operaRedis() throws Exception {
        User user = new User(1l, "李五哥", 25);
        redisTemplate.opsForValue().set("u1", user, 400, TimeUnit.SECONDS);
        User userFromDB = (User) redisTemplate.opsForValue().get("u1");
        System.err.println("=============userFromDB:" + userFromDB);
        
    }
    
}
