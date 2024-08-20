package com.ShoppingSite.service.userInterfaceService;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.userInterface.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart);
    public String updateShoppingCart(Integer userId, String username, List<Product> productsList);
    public ShoppingCart getShoppingCartByUsername(String username);
    public Boolean deleteShoppingCartByUsername(String username);
}
