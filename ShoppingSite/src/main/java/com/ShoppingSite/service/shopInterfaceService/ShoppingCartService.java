package com.ShoppingSite.service.shopInterfaceService;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.shopInterface.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart);
    public String updateShoppingCart(Integer userId, String username, List<Product> productsList);
    public ShoppingCart getShoppingCartByUsername(String username);
    public Boolean deleteShoppingCartByUsername(String username);
}
