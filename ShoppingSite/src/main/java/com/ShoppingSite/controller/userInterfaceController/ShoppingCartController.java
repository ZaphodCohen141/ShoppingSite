package com.ShoppingSite.controller.userInterfaceController;

import com.ShoppingSite.model.userInterface.ShoppingCart;
import com.ShoppingSite.service.userInterfaceService.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/shopping_cart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/create")
    public ResponseEntity<ShoppingCart> createCart(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart createdCart = shoppingCartService.createShoppingCart(shoppingCart);
        if (createdCart != null) {
            return ResponseEntity.ok(createdCart);
        } else {
            return ResponseEntity.badRequest().body(null); // Handle case where user does not exist
        }
    }

    @PutMapping("/update")
    public Integer updateCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.updateShoppingCart(shoppingCart);
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
}
