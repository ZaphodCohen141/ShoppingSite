package com.ShoppingSite.repository.shopnIterfaceRepository;

import com.ShoppingSite.model.shopInterface.Order;
import com.ShoppingSite.model.shopInterface.OrderStatus;

import java.util.List;

public interface OrderRepository {
    public Integer createOrder(Order order);
    public Order getOrderById(Integer orderId);
    public List<Order> getOrdersByUserId(Integer userId);
    public Integer updateOrderStatus(Integer orderId, OrderStatus orderStatus);
    public Integer deleteOrderById(Integer orderId);

}
