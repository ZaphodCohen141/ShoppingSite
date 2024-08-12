package com.ShoppingSite.service.Product;

import com.ShoppingSite.model.Product.Product;
import com.ShoppingSite.model.Product.ProductRequest;
import com.ShoppingSite.repository.productRepository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Integer createProduct(ProductRequest productRequest) throws Exception {
        Product existingProduct = productRepository.getProductByName(productRequest.getProductName());
        if(existingProduct != null){
            throw new Exception("Product " + productRequest.getProductName() + " already exist");
        }else {
            productRepository.createProduct(productRequest.toProduct());
            return productRepository.createProduct(productRequest.toProduct());
        }
    }

    @Override
    public void deleteProductById(Integer id) throws JsonProcessingException {
//        checks if product exists.
//        if exists - delete the product (!! need to be deleted also from userShoppingCart !!)
        Product product = productRepository.getProductById(id);
        if (product == null){
            System.out.println("there is no product with id " + id);
        }else {
            productRepository.deleteProductById(id);
//            !! ADD LATER - DELETE PRODUCT FROM SHOPPING CART !!
            System.out.println("product was deleted");
        }
    }

    @Override
    public Product getProductByName(String productName) {
        return null;
    }

    @Override
    public void updateProductByName(String productName, Product product) {

    }

}
