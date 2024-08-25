package com.ShoppingSite.controller.shopInterfaceController;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.service.shopInterfaceService.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;
    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestParam Integer userId, @RequestParam Integer productId) {
        String result = favoriteService.addFavorite(userId, productId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavorite(@RequestParam Integer userId, @RequestParam Integer productId) {
        String result = favoriteService.removeFavorite(userId, productId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Product>> getFavoriteProducts(@PathVariable Integer userId) {
        List<Product> favoriteProducts = favoriteService.getFavoriteProducts(userId);
        return ResponseEntity.ok(favoriteProducts);
    }
}
