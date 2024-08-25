package com.ShoppingSite.repository.shopnIterfaceRepository;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.shopInterface.ShoppingCart;
import com.ShoppingSite.repository.productRepository.ProductRepository;
import com.ShoppingSite.repository.shopnIterfaceRepository.shopInterfaceMappers.ShoppingCartMapper;
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
    ProductRepository productRepository;
    @Autowired
    FunctionUtil functionUtil;
    @Override
    public Integer createShoppingCart(ShoppingCart shoppingCart) {
        String sql = "INSERT INTO " + TableNamesUtil.SHOPPING_CART_TABLE_NAME +
                " (username, user_id, amount, state) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, shoppingCart.getUsername(), shoppingCart.getUserId(), shoppingCart.getAmount(),
                shoppingCart.getState());
        String getIdSql = "SELECT MAX(cart_id) FROM " + TableNamesUtil.SHOPPING_CART_TABLE_NAME;
        Integer cartId = jdbcTemplate.queryForObject(getIdSql, Integer.class);
        // Insert products into cart_products
        if (cartId != null && shoppingCart.getProductsList() != null) {
            for (Product product : shoppingCart.getProductsList()) {
                String productSql = "INSERT INTO " + TableNamesUtil.CART_PRODUCT_TABLE_NAME +
                        " (cart_id, product_id, quantity, price, status) VALUES (?, ?, ?, ?, ?)";
                jdbcTemplate.update(productSql, cartId, product.getId(), product.getQuantity(), product.getPrice(),
                        product.getStatus());
            }
        }
        return 1;
    }

    @Override
    public Integer updateShoppingCart(ShoppingCart shoppingCart) {
        String sql = "UPDATE " + TableNamesUtil.SHOPPING_CART_TABLE_NAME +
                " SET amount = ?, state = ? WHERE cart_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, shoppingCart.getAmount(), shoppingCart.getState(), shoppingCart.getCartId());

        // Optionally update products in the cart_products table
        // First, delete existing products in the cart
        String deleteProductsSql = "DELETE FROM " + TableNamesUtil.CART_PRODUCT_TABLE_NAME + " WHERE cart_id = ?";
        jdbcTemplate.update(deleteProductsSql, shoppingCart.getCartId());

        // Insert the updated products into cart_products
        if (shoppingCart.getProductsList() != null) {
            for (Product product : shoppingCart.getProductsList()) {
                String insertProductSql = "INSERT INTO " + TableNamesUtil.CART_PRODUCT_TABLE_NAME +
                        " (cart_id, product_id, quantity, price, status) VALUES (?, ?, ?, ?, ?)";
                Product dbProduct = productRepository.getProductByName(product.getProductName());
                jdbcTemplate.update(insertProductSql, shoppingCart.getCartId(), dbProduct.getId(), product.getQuantity(), dbProduct.getPrice(), dbProduct.getStatus());
            }
        }

        return rowsAffected;
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
        String getCartIdSql = "SELECT cart_id FROM " +
                TableNamesUtil.SHOPPING_CART_TABLE_NAME +
                " WHERE username = ?";
        Integer cartId = jdbcTemplate.queryForObject(getCartIdSql, new Object[]{username}, Integer.class);
        if (cartId != null) {
            String deleteCartSql = "DELETE FROM " + TableNamesUtil.SHOPPING_CART_TABLE_NAME + " WHERE cart_id = ?";
            return jdbcTemplate.update(deleteCartSql, cartId);
        } else {
            return 0;
        }
    }
}
