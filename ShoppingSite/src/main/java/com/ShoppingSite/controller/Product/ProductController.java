package com.ShoppingSite.controller.Product;

import com.ShoppingSite.model.Product.Product;
import com.ShoppingSite.model.Product.ProductRequest;
import com.ShoppingSite.service.Product.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {
   @Autowired
    ProductService productService;
    @PostMapping(value = "/private/createProduct")
    public Integer createProduct(@RequestBody ProductRequest productRequest) throws Exception {
        return productService.createProduct(productRequest);
    }
    @DeleteMapping(value = "private/deleteProduct")
    public void deleteProductById(@RequestParam Integer id) throws JsonProcessingException {
        productService.deleteProductById(id);
    }

    @PostMapping(value = "/public/getProductByName")
    public Product getProductByName(String productName) {
        return null;
    }
    @PostMapping(value = "private/updateProductByName")
    public void updateProductByName(String productName, Product product) {
    }
}
