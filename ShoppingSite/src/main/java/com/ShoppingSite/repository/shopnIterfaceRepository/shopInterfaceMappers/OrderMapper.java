package com.ShoppingSite.repository.shopnIterfaceRepository.shopInterfaceMappers;

import com.ShoppingSite.model.shopInterface.Order;
import com.ShoppingSite.model.shopInterface.OrderStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setOrderDate(rs.getDate("order_date"));
        order.setShippingAddress(rs.getString("shipping_address"));
        order.setTotalPrice(rs.getDouble("total_price"));
        order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
        return order;
    }
}
