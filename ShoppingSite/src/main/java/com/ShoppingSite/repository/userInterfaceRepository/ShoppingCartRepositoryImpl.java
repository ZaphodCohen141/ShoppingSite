package com.ShoppingSite.repository.userInterfaceRepository;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.userInterface.ShoppingCart;
import com.ShoppingSite.repository.userInterfaceRepository.shppingCartMapper.ShoppingCartMapper;
import com.ShoppingSite.utils.FunctionUtil;
import com.ShoppingSite.utils.TableNamesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    FunctionUtil functionUtil;
    @Override
    public Integer createShoppingCart(ShoppingCart shoppingCart) {
        String sql = "INSERT INTO " + TableNamesUtil.SHOPPING_CART_TABLE_NAME + " (username, user_id, amount, state) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, shoppingCart.getUsername(), shoppingCart.getUserId(), shoppingCart.getAmount(), shoppingCart.getState());

        // Retrieve the last inserted ID using MAX(cart_id)
        String getIdSql = "SELECT MAX(cart_id) FROM " + TableNamesUtil.SHOPPING_CART_TABLE_NAME;
        Integer cartId = jdbcTemplate.queryForObject(getIdSql, Integer.class);

        // Insert products into cart_products
        if (cartId != null && shoppingCart.getProductsList() != null) {
            for (Product product : shoppingCart.getProductsList()) {
                String productSql = "INSERT INTO " +
                        TableNamesUtil.CART_PRODUCT_TABLE_NAME + " (cart_id, product_id, quantity, price, status) VALUES (?, ?, ?, ?, ?)";
                jdbcTemplate.update(productSql, cartId, product.getId(), product.getQuantity(), product.getPrice(), product.getStatus());
            }
        }
        return 1; // Return a status or number of rows affected as needed
    }

    @Override
    public Integer updateShoppingCart(ShoppingCart shoppingCart) {
        String sql = "UPDATE " + TableNamesUtil.SHOPPING_CART_TABLE_NAME +
                " SET amount = ?, state = ? WHERE cart_id = ?";
        Integer dbShoppingCart = jdbcTemplate.update(sql, shoppingCart.getAmount(), shoppingCart.getState(), shoppingCart.getCartId());

        // Optionally update products in the cart_products table
        // First, delete existing products in the cart
        String deleteProductsSql = "DELETE FROM " + TableNamesUtil.CART_PRODUCT_TABLE_NAME + " WHERE cart_id = ?";
        jdbcTemplate.update(deleteProductsSql, shoppingCart.getCartId());

        // Insert the updated products into cart_products
        if (shoppingCart.getProductsList() != null) {
            for (Product product : shoppingCart.getProductsList()) {
                String insertProductSql = "INSERT INTO " + TableNamesUtil.CART_PRODUCT_TABLE_NAME +
                        " (cart_id, product_id, quantity, price, status) VALUES (?, ?, ?, ?, ?)";
                jdbcTemplate.update(insertProductSql, shoppingCart.getCartId(), product.getId(), product.getQuantity(), product.getPrice(), product.getStatus());
            }
        }

        return dbShoppingCart;
    }

    @Override
    public ShoppingCart getShoppingCartByUsername(String username) {
        String sql = "SELECT sc.cart_id, sc.username, sc.user_id, sc.amount, sc.state, " +
                "cp.product_id, p.productName, cp.quantity, cp.price, cp.status " +
                "FROM " + TableNamesUtil.SHOPPING_CART_TABLE_NAME + " sc " +
                "JOIN " + TableNamesUtil.CART_PRODUCT_TABLE_NAME + " cp ON sc.cart_id = cp.cart_id " +
                "JOIN " + TableNamesUtil.PRODUCT_TABLE_NAME + " p ON cp.product_id = p.id " +
                "WHERE sc.username = ?";

        List<ShoppingCart> carts = jdbcTemplate.query(sql, new Object[]{username}, new ShoppingCartMapper());
        return carts.isEmpty() ? null : carts.get(0);
    }

    @Override
    public Integer deleteShoppingCartByUsername(String username) {
        String deleteCartProductsSql = "DELETE FROM " +
                TableNamesUtil.CART_PRODUCT_TABLE_NAME +
                " WHERE cart_id = (SELECT cart_id FROM " +
                TableNamesUtil.SHOPPING_CART_TABLE_NAME +
                " WHERE username = ?)";
        jdbcTemplate.update(deleteCartProductsSql, username);

        String deleteCartSql = "DELETE FROM " + TableNamesUtil.SHOPPING_CART_TABLE_NAME + " WHERE username = ?";
        return jdbcTemplate.update(deleteCartSql, username);
    }
}
