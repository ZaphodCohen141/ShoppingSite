package com.ShoppingSite.repository.productRepository.productMapper;

import com.ShoppingSite.model.product.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("productName"),
                rs.getInt("quantity"),
                rs.getDouble("price")
        );
    }
}
