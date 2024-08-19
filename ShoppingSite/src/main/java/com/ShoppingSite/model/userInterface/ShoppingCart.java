package com.ShoppingSite.model.userInterface;

import com.ShoppingSite.model.product.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ShoppingCart {
    @JsonProperty("cartId")
    private Integer cartId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("userId")
    private Integer userId;
    private List<Product> productsList;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("state")
    private Integer state;

    public ShoppingCart() {
    }

    public ShoppingCart(Integer cartId, String username, Integer userId, List<Product> productsList, Integer amount, Integer state) {
        this.cartId = cartId;
        this.username = username;
        this.userId = userId;
        this.productsList = productsList;
        this.amount = amount;
        this.state = state;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
