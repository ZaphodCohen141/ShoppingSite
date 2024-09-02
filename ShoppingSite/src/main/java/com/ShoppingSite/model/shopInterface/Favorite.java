package com.ShoppingSite.model.shopInterface;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Favorite {
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("productId")
    private Integer productId;

    public Favorite() {
    }

    public Favorite(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    // Getters and Setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
