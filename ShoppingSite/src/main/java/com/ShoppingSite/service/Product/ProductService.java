package com.ShoppingSite.service.Product;

import com.ShoppingSite.model.Product.Product;
import com.ShoppingSite.model.Product.ProductRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductService {
    public Integer createProduct(ProductRequest productRequest) throws Exception;
    public void deleteProductById(Integer id) throws JsonProcessingException;
    public Product getProductByName(String productName);
    public  void updateProductByName(String productName, Product product);
}
