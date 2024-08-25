package com.ShoppingSite.repository.shopnIterfaceRepository.shopInterfaceMappers;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.shopInterface.ShoppingCart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartMapper implements RowMapper<ShoppingCart> {

    @Override
    public ShoppingCart mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(rs.getInt("cart_id"));
        shoppingCart.setUsername(rs.getString("username"));
        shoppingCart.setUserId(rs.getInt("user_id"));
        shoppingCart.setAmount(rs.getDouble("amount"));
        shoppingCart.setState(rs.getInt("state"));

        // Assuming there's a method to extract products
        List<Product> products = extractProducts(rs);
        shoppingCart.setProductsList(products);

        return shoppingCart;
    }


    private List<Product> extractProducts(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        do {
            Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("productName"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getInt("status")
            );
            products.add(product);
        } while (rs.next());

        return products;
    }

}
