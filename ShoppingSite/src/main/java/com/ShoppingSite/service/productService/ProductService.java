package com.ShoppingSite.service.productService;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.product.ProductRequest;

import java.util.List;

public interface ProductService {
    public Integer createProduct(ProductRequest productRequest) throws Exception;
    public void deleteProductById(Integer id) throws Exception;
    public Product getProductByName(String productName);
    public  String updateProductByName(String productName, Product product);
    public List<Product> findProducts (String product);
    public List<Product> getAllProducts();
    public List<Product> getProductsByNumber(int limit);
    public String updateProductImageUrl(String productName);
}
