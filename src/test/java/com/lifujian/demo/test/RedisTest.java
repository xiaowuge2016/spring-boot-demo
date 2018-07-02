package com.lifujian.demo.test;

import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.lifujian.demo.model.User;
import com.lifujian.demo.service.UserService;

/**
 * @author lifujian
 * @date  2018/07/02 16:49
 * @description 测试 redis 存储字符串和存储 java对象
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private UserService userService;
    
    @Test
    public void t01_operaRedis() throws Exception {
        stringRedisTemplate.opsForValue().set("name", "李五哥", 10, TimeUnit.SECONDS);
        String name = stringRedisTemplate.opsForValue().get("name");
        Assertions.assertThat(name).isEqualTo("李五哥");
        
    }
    
    @Test
    public void t02_operaRedis() throws Exception {
        User user = new User(11l, "李五哥", 25);
        redisTemplate.opsForValue().set("u11", user, 10, TimeUnit.SECONDS);
        User userFromDB = (User) redisTemplate.opsForValue().get("u11");
        Assertions.assertThat(userFromDB.getName()).isEqualTo("李五哥");
    }
    
    //使用Redis缓存对象，getUserById只会被调用一次, 可查看控制台打印信息
    @Test
    public void t03_operaRedisCatch() {
        User user = new User();
        System.out.println("======t03=======");
        user = userService.getUserById(1l);
        user = userService.getUserById(1l);
        user = userService.getUserById(1l);
        System.out.println("======t03=======");
        Assertions.assertThat(user.getName()).isEqualTo("张三");
    }

    @Test
    public void t04_insert() {
        User user = new User(UserService.generateId(), "王大锤", 28);
        User res = userService.createUser(user);
        Assertions.assertThat(res.getName()).isEqualTo("王大锤");
        // 验证缓存
        User userFromDB = userService.getUserById(user.getId());
        Assertions.assertThat(userFromDB.getName()).isEqualTo("王大锤");
    }
    
    @Test
    public void t05_update() {
        User res = userService.updateNameById(1l, "张老三");
        Assertions.assertThat(res.getName()).isEqualTo("张老三");
        // 验证缓存
        User userFromDB = userService.getUserById(1l);
        userFromDB = userService.getUserById(1l);
        userFromDB = userService.getUserById(1l);
        Assertions.assertThat(userFromDB.getName()).isEqualTo("张老三");
    }
    
    @Test
    public void t06_delete() {
        User res = userService.deleteUserById(3l);
        Assertions.assertThat(res.getName()).isEqualTo("王五");
        // 验证缓存
        User userFromDB = userService.getUserById(3l);
        Assertions.assertThat(userFromDB).isNull();
    }
}
