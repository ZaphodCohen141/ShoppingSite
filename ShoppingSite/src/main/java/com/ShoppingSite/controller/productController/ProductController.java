package com.ShoppingSite.controller.productController;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.product.ProductRequest;
import com.ShoppingSite.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(value = "/private/create")
    public Integer createProduct(@RequestBody ProductRequest productRequest) throws Exception {
        return productService.createProduct(productRequest);
    }

    @DeleteMapping(value = "/private/delete")
    public void deleteProductById(@RequestParam Integer id) throws Exception {
        productService.deleteProductById(id);
    }

    @GetMapping(value = "/public/getProductByName")
    public Product getProductByName(@RequestParam String productName) {
        return productService.getProductByName(productName);
    }

    @PostMapping(value = "/private/updateProductByName")
    public void updateProductByName(@RequestParam String productName, @RequestBody Product product) {
        productService.updateProductByName(productName, product);
    }

    @GetMapping("/public/searchProducts/{productName}")
    public List<Product> findProducts(@PathVariable("productName") String productName) {
        return productService.findProducts(productName);
    }
    @GetMapping("/public/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/public/getProducts")
    public ResponseEntity<List<Product>> getProductsWithLim(@RequestParam int limit) {
        List<Product> products = productService.getProductsByNumber(limit);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/updateProductImageUrl")
    public String updateProductImageUrl(@RequestParam String productName) {
        return productService.updateProductImageUrl(productName);
    }

}

