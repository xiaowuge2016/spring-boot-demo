package com.lifujian.demo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lifujian.demo.model.Product;

/**
 * @author lifujian
 * @date 2018年6月20日
 * @description
 */
public class ProductResponse {

    @JsonProperty("items")
    private List<Product> productList;
    
    private int total;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}
