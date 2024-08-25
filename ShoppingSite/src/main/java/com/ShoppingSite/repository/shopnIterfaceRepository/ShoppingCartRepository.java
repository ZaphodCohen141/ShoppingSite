package com.ShoppingSite.repository.shopnIterfaceRepository;

import com.ShoppingSite.model.shopInterface.ShoppingCart;

public interface ShoppingCartRepository {
    public Integer createShoppingCart(ShoppingCart shoppingCart);
    public Integer updateShoppingCart(ShoppingCart shoppingCart);
    public ShoppingCart getShoppingCartByUsername(String username);
    public Integer deleteShoppingCartByUsername(String username);
}
