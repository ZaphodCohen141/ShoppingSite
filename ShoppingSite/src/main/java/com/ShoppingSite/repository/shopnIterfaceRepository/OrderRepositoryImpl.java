package com.ShoppingSite.repository.shopnIterfaceRepository;

import com.ShoppingSite.model.shopInterface.Order;
import com.ShoppingSite.model.shopInterface.OrderStatus;
import com.ShoppingSite.repository.shopnIterfaceRepository.shopInterfaceMappers.OrderMapper;
import com.ShoppingSite.utils.TableNamesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    OrderMapper orderMapper;
    public Integer createOrder(Order order) {
        String sql = "INSERT INTO " + TableNamesUtil.ORDERS_TABLE_NAME +
        " (user_id, order_date, shipping_address, total_price, order_status) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, order.getUserId(), order.getOrderDate(), order.getShippingAddress(), order.getTotalPrice(), order.getOrderStatus().name());
    }

    public Order getOrderById(Integer orderId) {
        String sql = "SELECT * FROM " + TableNamesUtil.ORDERS_TABLE_NAME + " WHERE order_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{orderId}, orderMapper);
    }

    public List<Order> getOrdersByUserId(Integer userId) {
        String sql = "SELECT * FROM " + TableNamesUtil.ORDERS_TABLE_NAME + " WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, orderMapper);
    }

    public Integer updateOrderStatus(Integer orderId, OrderStatus orderStatus) {
        String sql = "UPDATE " + TableNamesUtil.ORDERS_TABLE_NAME + " SET order_status = ? WHERE order_id = ?";
        return jdbcTemplate.update(sql, orderStatus.name(), orderId);
    }

    public Integer deleteOrderById(Integer orderId) {
        String sql = "DELETE FROM " + TableNamesUtil.ORDERS_TABLE_NAME + " WHERE order_id = ?";
        return jdbcTemplate.update(sql, orderId);
    }
}
