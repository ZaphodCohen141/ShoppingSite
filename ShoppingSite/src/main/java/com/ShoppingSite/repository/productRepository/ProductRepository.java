package com.ShoppingSite.repository.productRepository;

import com.ShoppingSite.model.product.Product;

import java.util.List;

public interface ProductRepository {
    public Integer createProduct(Product product);
    public void deleteProductById(Integer id);
    public Product getProductByName(String productName);
    public String updateProductByName(String productName, Product product);
    public Product getProductById (Integer id) throws Exception;
    public List<Product> findProducts (String product);
    public Integer updateProductQuantity(Product product);
}
