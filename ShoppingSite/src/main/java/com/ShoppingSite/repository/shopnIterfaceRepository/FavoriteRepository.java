package com.ShoppingSite.repository.shopnIterfaceRepository;

import com.ShoppingSite.model.product.Product;

import java.util.List;

public interface FavoriteRepository {
    public Integer addFavorite(Integer userId, Integer productId);
    public Integer removeFavorite(Integer userId, Integer productId);
    public List<Product> getFavoriteProductsByUserId(Integer userId);
    public void deleteFavoriteOfUser(Integer userId);
}
