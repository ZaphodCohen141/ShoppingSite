package com.ShoppingSite.repository.userInterfaceRepository;

import com.ShoppingSite.model.userInterface.ShoppingCart;

public interface ShoppingCartRepository {
    public Integer createShoppingCart(ShoppingCart shoppingCart);
    public Integer updateShoppingCart(ShoppingCart shoppingCart);
    public ShoppingCart getShoppingCartByUsername(String username);
    public Integer deleteShoppingCartByUsername(String username);
}
