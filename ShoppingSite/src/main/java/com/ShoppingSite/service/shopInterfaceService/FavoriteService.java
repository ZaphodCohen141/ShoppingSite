package com.ShoppingSite.service.shopInterfaceService;

import com.ShoppingSite.model.product.Product;

import java.util.List;

public interface FavoriteService {
    public String addFavorite(Integer userId, Integer productId);
    public String removeFavorite(Integer userId, Integer productId);
    public List<Product> getFavoriteProducts(Integer userId);
    public void deleteFavoriteOfUser(Integer userId);
}
