package com.ShoppingSite.repository.shopnIterfaceRepository;

import com.ShoppingSite.model.shopInterface.ShoppingCart;

public interface ShoppingCartRepository {
    public Integer createShoppingCart(ShoppingCart shoppingCart);
    public Integer updateShoppingCart(ShoppingCart shoppingCart) throws Exception;
    public ShoppingCart getShoppingCartByUsername(String username);
    public Integer deleteShoppingCartByUsername(String username);
    public boolean checkIfCartExist(String username);
    public void addProductToCart(Integer cartId, Integer productId, Integer quantity, Double price, Integer status);
    public Integer getCartIdByUsername(String username);
    public int updateProductInCart(Integer cartId, Integer productId, int quantity);
}
