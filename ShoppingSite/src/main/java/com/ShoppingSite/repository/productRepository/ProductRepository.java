package com.ShoppingSite.repository.productRepository;

import com.ShoppingSite.model.product.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductRepository {
    public Integer createProduct(Product product);
    public void deleteProductById(Integer id);
    public Product getProductByName(String productName);
    public String updateProductByName(String productName, Product product);
    public Product getProductById (Integer id) throws JsonProcessingException;
}
