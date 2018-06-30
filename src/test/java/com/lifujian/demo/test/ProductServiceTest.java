package com.lifujian.demo.test;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lifujian.demo.model.Product;
import com.lifujian.demo.service.ProductService;

/**
 * @author lifujian
 * @date 2018年6月20日
 * @description 服务类测试
 */
@RunWith(SpringRunner.class)  // 确保 Junit 能与 SpringBoot 框架结合
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)  // 按方法名的顺序执行。否者会随机执行
public class ProductServiceTest {

    
    @Autowired
    private ProductService productService;
    
    @Test
    public void t1_getProductById() throws Exception {
        Product product = productService.getProductById(1);
        Assert.assertNotNull("product is null.", product);
        
    }
    
    @Test
    public void t2_createProduct() throws Exception {
        Product product = productService.createProduct("iMac", 8000);
        Assert.assertNotEquals("product is null.", product);
    }
}
