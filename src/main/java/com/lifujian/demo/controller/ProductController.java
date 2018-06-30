package com.lifujian.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lifujian.demo.model.Product;
import com.lifujian.demo.request.ProductRequest;
import com.lifujian.demo.response.ProductResponse;
import com.lifujian.demo.service.ProductService;

/**
 * @author lifujian
 * @date  2018年6月20日
 * @description 
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }
    
    @PostMapping("/product")
    public Product createProduct(@RequestBody ProductRequest productRequest) {
        String name = productRequest.getName();
        int price = productRequest.getPrice();
        return productService.createProduct(name, price);
    }
    
    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Map<String, Object> fieldMap) {
        return productService.updateProduct(id, fieldMap);
    }
    
    @GetMapping("/product")
    public ProductResponse getAllProducts() {
        List<Product> productList = productService.getProductList(); 
        ProductResponse response = new ProductResponse();
        response.setProductList(productList);
        response.setTotal(productList.size());
        return response;
    }
}
 