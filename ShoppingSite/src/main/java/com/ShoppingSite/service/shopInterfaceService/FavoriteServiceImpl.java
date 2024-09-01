package com.ShoppingSite.service.shopInterfaceService;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.repository.shopnIterfaceRepository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService{
    @Autowired
    FavoriteRepository favoriteRepository;
    public String addFavorite(Integer userId, Integer productId) {
        List<Product> favorites = favoriteRepository.getFavoriteProductsByUserId(userId);
        for (Product product : favorites) {
            if (product.getId().equals(productId)) {
                return "Product is already in the favorite list.";
            }
        }
        favoriteRepository.addFavorite(userId, productId);
        return "Product added to the favorite list.";
    }

    public String removeFavorite(Integer userId, Integer productId) {
        favoriteRepository.removeFavorite(userId, productId);
        return "Product removed from the favorite list.";
    }

    public List<Product> getFavoriteProducts(Integer userId) {
        return favoriteRepository.getFavoriteProductsByUserId(userId);
    }
    public void deleteFavoriteOfUser(Integer userId){
        favoriteRepository.deleteFavoriteOfUser(userId);
    }
}
