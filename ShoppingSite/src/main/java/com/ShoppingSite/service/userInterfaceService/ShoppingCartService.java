package com.ShoppingSite.service.userInterfaceService;

import com.ShoppingSite.model.userInterface.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartService {
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart);
    public Integer updateShoppingCart(ShoppingCart shoppingCart);
    public ShoppingCart getShoppingCartByUsername(String username);
    public Boolean deleteShoppingCartByUsername(String username);
}
