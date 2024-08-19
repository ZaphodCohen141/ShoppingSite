package com.ShoppingSite.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("price")
    private Double price;

    public ProductRequest(String productName) {
        this.productName = productName;
    }

    public Product toProduct(){
        return new Product(
                this.productName,
                this.quantity,
                this.price
        );
    }
    public Product productStatus(){
        return new Product(
                this.productName,
                this.quantity,
                this.price
        );
    }

    public ProductRequest() {
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
