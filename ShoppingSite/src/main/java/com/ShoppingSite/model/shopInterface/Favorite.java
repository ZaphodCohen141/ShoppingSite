package com.ShoppingSite.model.shopInterface;

public class Favorite {

    private Integer userId;
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
