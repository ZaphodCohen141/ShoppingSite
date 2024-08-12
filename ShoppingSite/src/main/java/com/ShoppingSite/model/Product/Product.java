package com.ShoppingSite.model.Product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("price")
    private Double price;

    public Product() {
    }

    public Product(Integer id, String productName, Integer quantity, Double price) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String productName, Integer quantity, Double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

