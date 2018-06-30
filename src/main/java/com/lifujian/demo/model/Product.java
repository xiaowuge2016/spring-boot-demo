package com.lifujian.demo.model;

/**
 * @author lifujian
 * @date 2018年6月20日
 * @description 产品 实体类
 */
public class Product {
    
    private long id;
    private String name;
    private int price;
    private long created;

    public Product() {
    }
    
    public Product(long id, String name, int price, long created) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.created = created;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", created=" + created + "]";
    }

}
