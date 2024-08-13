package com.ShoppingSite.service.product;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.product.ProductRequest;
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
            return productRepository.createProduct(productRequest.toProduct());
        }
    }

    @Override
    public void deleteProductById(Integer id) throws JsonProcessingException {
//        checks if product exists.
//        if exists - delete the product (!! need to be deleted also from userShoppingCart !!)
        Product product = productRepository.getProductById(id);
        if (product != null){
            productRepository.deleteProductById(id);
//            !! ADD LATER - DELETE PRODUCT FROM SHOPPING CART !!
            System.out.println("product was deleted");

        }else {
            System.out.println("there is no product with id " + id);
        }
    }

    @Override
    public Product getProductByName(String productName) {
        //        checks if product exists.
//        if exists - get the product
        Product product = productRepository.getProductByName(productName);
        if (product != null){
            return productRepository.getProductByName(productName);
        }else {
            System.out.println("there isn't product named " + productName);
            return null;
        }
    }

    @Override
    public String updateProductByName(String productName, Product product) {
        //        if exists - update the product. if not - sout to create new product
        Product productFromDb = productRepository.getProductByName(productName);
        if (productFromDb != null){
            return productRepository.updateProductByName(productName,product);
        }else {
            System.out.println("there isn't product named " + productName);
            return null;
        }
    }

}
