package com.ShoppingSite.model.product;

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
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("imageUrl")
    private String imageUrl;

    public Product() {
    }

    public Product(Integer id, String productName, Integer quantity, Double price, Integer status, String imageUrl) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.imageUrl = imageUrl;
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

    public Product(Integer id, Integer quantity, Integer status) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
    }

    public Product(Integer id, String productName, Integer quantity, Double price, Integer status) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public Product(String productName, Integer quantity) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}

