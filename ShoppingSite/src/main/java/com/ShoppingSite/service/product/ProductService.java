package com.ShoppingSite.service.product;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.product.ProductRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductService {
    public Integer createProduct(ProductRequest productRequest) throws Exception;
    public void deleteProductById(Integer id) throws JsonProcessingException;
    public Product getProductByName(String productName);
    public  String updateProductByName(String productName, Product product);
}
