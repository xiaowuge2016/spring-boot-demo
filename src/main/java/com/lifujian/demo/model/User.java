package com.lifujian.demo.model;

import java.io.Serializable;

/**
 * @author lifujian
 * @date 2018/07/02 14:12
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private int age;
    
    // 对象序列化时，需要用到空构造器
    public User() {
    }

    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

}
