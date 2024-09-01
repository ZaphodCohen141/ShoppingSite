package com.ShoppingSite.service.shopInterfaceService;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.shopInterface.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) throws Exception;
    public String updateShoppingCart(Integer userId, String username, List<Product> productsList) throws Exception;
    public ShoppingCart getShoppingCartByUsername(String username);
    public Boolean deleteShoppingCartByUsername(String username);
    public boolean checkIfCartExist(String username);
    public int updateProductInCart(Integer cartId, Integer productId, int quantity);
    public String addProductToCart(String username, Integer productId) throws Exception;
}
