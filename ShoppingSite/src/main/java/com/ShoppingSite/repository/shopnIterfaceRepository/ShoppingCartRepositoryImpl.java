package com.ShoppingSite.repository.shopnIterfaceRepository;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.shopInterface.ShoppingCart;
import com.ShoppingSite.repository.productRepository.ProductRepository;
import com.ShoppingSite.repository.shopnIterfaceRepository.shopInterfaceMappers.ShoppingCartMapper;
import com.ShoppingSite.utils.FunctionUtil;
import com.ShoppingSite.utils.TableNamesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        if (cartId != null && shoppingCart.getProductsList() != null) {
            for (Product product : shoppingCart.getProductsList()) {
                String productSql = "INSERT INTO " + TableNamesUtil.CART_PRODUCT_TABLE_NAME +
                        " (cart_id, product_id, quantity, price, status) VALUES (?, ?, ?, ?, ?)";
                jdbcTemplate.update(productSql, cartId, product.getId(), product.getQuantity(), product.getPrice(),
                        product.getStatus());
            }
        }
        return cartId;
    }

    @Override
    public Integer updateShoppingCart(ShoppingCart shoppingCart) throws Exception {
        String sql = "UPDATE " + TableNamesUtil.SHOPPING_CART_TABLE_NAME +
                " SET amount = ?, state = ? WHERE cart_id = ?";
        int changes = jdbcTemplate.update(sql, shoppingCart.getAmount(), shoppingCart.getState(),
                shoppingCart.getCartId());

        String deleteProductsSql = "DELETE FROM " + TableNamesUtil.CART_PRODUCT_TABLE_NAME + " WHERE cart_id = ?";
        jdbcTemplate.update(deleteProductsSql, shoppingCart.getCartId());

        if (shoppingCart.getProductsList() != null) {
            for (Product product : shoppingCart.getProductsList()) {
                String insertProductSql = "INSERT INTO " + TableNamesUtil.CART_PRODUCT_TABLE_NAME +
                        " (cart_id, product_id, quantity, price, status) VALUES (?, ?, ?, ?, ?)";
                Product dbProduct = productRepository.getProductById(product.getId());
                jdbcTemplate.update(insertProductSql, shoppingCart.getCartId(), dbProduct.getId(),
                        product.getQuantity(), dbProduct.getPrice(), dbProduct.getStatus());
            }
        }

        return changes;
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
    public void addProductToCart(Integer cartId, Integer productId, Integer quantity, Double price, Integer status) {
        String sql = "INSERT INTO " + TableNamesUtil.CART_PRODUCT_TABLE_NAME +
                " (cart_id, product_id, quantity, price, status) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cartId, productId, quantity, price, status);
    }
    @Override
    public int updateProductInCart(Integer cartId, Integer productId, int quantity) {
        String sql = "UPDATE " + TableNamesUtil.CART_PRODUCT_TABLE_NAME +
                " SET quantity = ? " +
                "WHERE cart_id = ? AND product_id = ?";
        return jdbcTemplate.update(sql, quantity, cartId, productId);
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

    @Override
    public boolean checkIfCartExist(String username) {
        String sql = "SELECT COUNT(*) FROM " +
                TableNamesUtil.SHOPPING_CART_TABLE_NAME +
                " WHERE LOWER(username) = LOWER(?)";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false; // if no cart exist return false
        }
    }
    @Override
    public Integer getCartIdByUsername(String username) {
        String sql = "SELECT cart_id FROM " + TableNamesUtil.SHOPPING_CART_TABLE_NAME +
                " WHERE LOWER(username) = LOWER(?)";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Integer> getCartIdsByUsername(String username) {
        String sql = "SELECT cart_id FROM " + TableNamesUtil.SHOPPING_CART_TABLE_NAME +
                " WHERE LOWER(username) = LOWER(?)";
        return jdbcTemplate.queryForList(sql, new Object[]{username}, Integer.class);
    }

    @Override
    public int deleteShoppingCartById(Integer cartId) {
        String deleteCartSql = "DELETE FROM " + TableNamesUtil.SHOPPING_CART_TABLE_NAME + " WHERE cart_id = ?";
        return jdbcTemplate.update(deleteCartSql, cartId);
    }

}
