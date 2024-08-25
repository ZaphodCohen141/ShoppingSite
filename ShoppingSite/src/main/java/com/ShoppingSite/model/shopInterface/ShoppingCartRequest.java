package com.ShoppingSite.model.shopInterface;


import com.ShoppingSite.model.product.ProductRequest;

import java.util.List;

public class ShoppingCartRequest {
    private Integer userId;
    private String username;
    private List<ProductRequest> productsList;

    // Getters and Setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ProductRequest> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductRequest> productsList) {
        this.productsList = productsList;
    }
}

