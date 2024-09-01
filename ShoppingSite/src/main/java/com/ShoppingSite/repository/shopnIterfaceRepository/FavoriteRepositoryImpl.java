package com.ShoppingSite.repository.shopnIterfaceRepository;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.repository.productRepository.productMapper.ProductMapper;
import com.ShoppingSite.utils.TableNamesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;
    public Integer addFavorite(Integer userId, Integer productId) {
        String sql = "INSERT INTO " + TableNamesUtil.FAVORITES_TABLE_NAME + " (user_id, product_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, userId, productId);
    }
    public Integer removeFavorite(Integer userId, Integer productId) {
        String sql = "DELETE FROM " + TableNamesUtil.FAVORITES_TABLE_NAME + " WHERE user_id = ? AND product_id = ?";
        return jdbcTemplate.update(sql, userId, productId);
    }
    public List<Product> getFavoriteProductsByUserId(Integer userId) {
        String sql = "SELECT p.* FROM " + TableNamesUtil.PRODUCT_TABLE_NAME + " p " +
                "JOIN " + TableNamesUtil.FAVORITES_TABLE_NAME + " f ON p.id = f.product_id " +
                "WHERE f.user_id = ?";
        System.out.println(userId);
        return jdbcTemplate.query(sql, new ProductMapper(), userId);
    }
}
