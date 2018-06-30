package com.lifujian.demo.test;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.JsonPath;
import com.lifujian.demo.model.Product;
import com.lifujian.demo.request.ProductRequest;

/**
 * @author lifujian
 * @date  2018/06/30 10:40
 * @description 控制层测试类
 *  使用 Assertj / JSONAssert / JsonPath 进行测试，而不是使用 junit
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 使用随机端口进行测试
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按方法名顺序 运行
public class ProductControllerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;  // 仅在测试环境使用，用于发请求
    
    /**
     * Assertj 逐个属性进行判断，但属性过多时就很麻烦了
     */
    @Test
    public void t01_getProductById() throws Exception {
        Product product = restTemplate.getForObject("/product/1", Product.class);
        // Assertj 的流式写法要更友好
        Assertions.assertThat(product.getId()).isEqualTo(1l);
        Assertions.assertThat(product.getName()).isEqualTo("MacBook");
        Assertions.assertThat(product.getPrice()).isEqualTo(10000);
    }
    
    /**
     * 直接判断 toString()。但对于复杂的 JSON, 仍然麻烦
     */
    @Test
    public void t02_getPorductById() throws Exception {
        String expected = "\"id\":1,\"name\":\"MacBook\",\"price\":10000";
        String actual = restTemplate.getForObject("/product/1", String.class);
        System.out.println("t02--" + actual);
        Assertions.assertThat(actual).contains(expected);
    }
    
    /**
     * 使用 JSONAssert.  可按照非严格的方式对 JSON 字符串进行断言
     */
    @Test
    public void t03_createProduct() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("new MacBook");
        request.setPrice(11000);
        // 此处必须是一个 json 字符串
        String expected = "{\"id\":4,\"name\":\"new MacBook\",\"price\":11000}";
        String actual = restTemplate.postForObject("/product", request, String.class);
        System.out.println("t03--" + actual);
        JSONAssert.assertEquals(expected, actual, false); // 最后一个参数 是否启用严格的检查。若启用，会因为 少 created 属性而检查不通过。
    }

    /**
     * 尝试使用 JsonPath
     */
    @Test
    public void t04_updateProduct() throws Exception {
        String expected = "{\"id\":1,\"name\":\"MacBook\",\"price\":9400}";
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("price", 9400);
        RequestEntity<Map<String, Object>> requestEntity = new RequestEntity<>(fieldMap, HttpMethod.PUT, new URI("/product/1"));
        String actual = restTemplate.exchange(requestEntity, String.class).getBody();
        System.out.println("t04--" + actual);
        JSONAssert.assertEquals(expected, actual, false);
        
        int price = JsonPath.read(actual, "$.price");
        Assertions.assertThat(price).isEqualByComparingTo(9400);
    }
}
 