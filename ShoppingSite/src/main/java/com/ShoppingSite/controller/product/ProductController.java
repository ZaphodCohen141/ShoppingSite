package com.ShoppingSite.controller.product;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.product.ProductRequest;
import com.ShoppingSite.service.product.ProductService;
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

    @GetMapping(value = "/public/getProductByName")
    public Product getProductByName(@RequestParam String productName) {
        return  productService.getProductByName(productName);
    }
    @PostMapping(value = "private/updateProductByName")
    public void updateProductByName(@RequestParam String productName, @RequestBody Product product) {
        productService.updateProductByName(productName,product);
    }
}
