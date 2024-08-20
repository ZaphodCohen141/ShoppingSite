package com.ShoppingSite.controller.userInterfaceController;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.product.ProductRequest;
import com.ShoppingSite.model.userInterface.ShoppingCart;
import com.ShoppingSite.model.userInterface.ShoppingCartRequest;
import com.ShoppingSite.service.userInterfaceService.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/public/shopping_cart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;


    @PostMapping("/create")
    public ResponseEntity<ShoppingCart> createCart(@RequestBody ShoppingCartRequest request) {
        // convert List<ProductRequest> to List<Product>
        List<Product> productsList = request.getProductsList().stream()
                .map(ProductRequest::toProduct)
                .collect(Collectors.toList());

        // convert to ShoppingCart
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(request.getUserId());
        shoppingCart.setUsername(request.getUsername());
        shoppingCart.setProductsList(productsList);

        ShoppingCart createdCart = shoppingCartService.createShoppingCart(shoppingCart);
        if (createdCart != null) {
            return ResponseEntity.ok(createdCart);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable String username) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUsername(username);
        if (shoppingCart != null) {
            return ResponseEntity.ok(shoppingCart);
        } else {
            return ResponseEntity.notFound().build(); // Handle case where user does not exist
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteCart(@PathVariable String username) {
        boolean deleted = shoppingCartService.deleteShoppingCartByUsername(username);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build(); // Handle case where user does not exist
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCart(@RequestBody ShoppingCartRequest request) {
        // Convert List<ProductRequest> to List<Product> using toProduct() method
        List<Product> productsList = request.getProductsList().stream()
                .map(ProductRequest::toProduct)
                .collect(Collectors.toList());

        String result = shoppingCartService.updateShoppingCart(request.getUserId(), request.getUsername(), productsList);
        if (result.equals("Shopping cart updated successfully.")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}