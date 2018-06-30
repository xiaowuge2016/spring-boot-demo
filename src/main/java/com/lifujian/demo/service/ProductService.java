package com.lifujian.demo.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.lifujian.demo.model.Product;

/**
 * @author lifujian
 * @date  2018年6月20日
 * @description 服务类
 */
@Service
public class ProductService {
    
    private final static List<Product> productList = new ArrayList<>();
    
    // 构造测试数据
    private final static AtomicInteger idGenerator = new AtomicInteger(1);
    
    static {
        productList.add(new Product(generateId(), "MacBook", 10000, getCurrentTime()));
        productList.add(new Product(generateId(), "MacBook Air", 7000, getCurrentTime()));
        productList.add(new Product(generateId(), "MacBook Pro", 12000, getCurrentTime()));
    }
    
    private static int generateId() {
        return idGenerator.getAndIncrement();
    }

    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }
    //---end---
    
    public Product getProductById(long id) {
        for (Product product : productList) {
            if (id == product.getId()) {
                return product;
            }
        }
        return null;
    }
    
    /**
     * 创建
     */
    public Product createProduct(String name, int price) {
        Product product = new Product(generateId(), name, price, getCurrentTime());
        boolean result = productList.add(product);
        if (result) {
            return product;
        }
        return null;
    }
    
    /**
     * 更新
     */
    public Product updateProduct(long id, Map<String, Object> fieldMap) {
        Product product = getProductById(id);
        if (product != null) {
            for (Map.Entry<String, Object> fieldEntry : fieldMap.entrySet()) {
                Object fielddValue = fieldEntry.getValue();
                if (fielddValue != null) {
                    String fieldName = fieldEntry.getKey();
                    // 使用反射
                    Field field = ReflectionUtils.findField(Product.class, fieldName);
                    ReflectionUtils.makeAccessible(field); // 使字段可访问
                    ReflectionUtils.setField(field, product, fielddValue);
                }
            }
            return product;
        }
        return null;
    }
    
    public List<Product> getProductList() {
        return null;
    }
}
 