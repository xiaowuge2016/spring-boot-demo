package com.lifujian.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lifujian.demo.model.User;

/**
 * @author lifujian
 * @date  2018/07/02 20:52
 * @description 使用 redis 缓存
 */
@Service
@CacheConfig(cacheNames="user")
public class UserService {
    
    private final static List<User> userList = new ArrayList<>();
    
    // 构造测试数据
    private final static AtomicInteger idGenerator = new AtomicInteger(1);
    
    static {
        userList.add(new User(generateId(), "张三", 22));
        userList.add(new User(generateId(), "李四", 23));
        userList.add(new User(generateId(), "王五", 24));
    }
    
    public static int generateId() {
        return idGenerator.getAndIncrement();
    }
    //---end---
    
    /**
     * Cacheable 适用于获取数据，如果缓存中有数据，不再调用注解方法
     * unless = "#result == null"  意为若 value 为 null, 则不缓存
     * key 格式："hello_user_1", 其中前缀 'hello_' 在 BootConfig 里面配置
     */
    @Cacheable(key = "'user_'+#id", unless = "#result == null")
    public User getUserById(long id) {
        for (User user : userList) {
            if (id == user.getId()) {
                System.out.println("====从数据库中取数据了====" + user.getName());
                return user;
            }
        }
        return null;
    }
    
    /**
     * CachePut 适合于插入数据和更新数据。一定会调用真实方法，再将方法返回值保存到缓存
     */
    @CachePut(key = "'user_'+#user.id")
    public User createUser(User user) {
        boolean result = userList.add(user);
        if (result) {
            System.out.println("====向数据库中插入了数据====" + user);
            return user;
        }
        return null;
    }
    
    @CachePut(key = "'user_'+#id")
    public User updateNameById(long id, String name) {
        User user = getUserById(id);
        userList.remove(user);
        user.setName(name);
        boolean result = userList.add(user);
        if (result) {
            return getUserById(id);
        }
        return null;
    }
    
    /**
     * CacheEvict 适合于删除数据。
     */
    @CacheEvict(key = "'user_'+#id")
    public User deleteUserById(long id) {
        User user = getUserById(id);
        boolean result = userList.remove(user);
        if (result) {
            return user;
        }
        return null;
    }

}
 