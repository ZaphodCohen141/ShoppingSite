package com.ShoppingSite.service.shopInterfaceService;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.shopInterface.Order;
import com.ShoppingSite.model.shopInterface.OrderStatus;
import com.ShoppingSite.repository.shopnIterfaceRepository.OrderRepository;
import com.ShoppingSite.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    ProductService productService;
    public Integer createOrder(Order order) {
        order.setOrderDate(new java.sql.Date(System.currentTimeMillis())); // Set the order date to now
        order.setOrderStatus(OrderStatus.TEMP); // Set the initial status to TEMP
        return orderRepository.createOrder(order);
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public List<Order> getOrdersByUserId(Integer userId) {
        return orderRepository.getOrdersByUserId(userId);
    }

    public Integer updateOrderStatus(Integer orderId, OrderStatus orderStatus) {
        if (orderStatus == OrderStatus.CLOSED){

        }
        return orderRepository.updateOrderStatus(orderId, orderStatus);
    }

    public Integer deleteOrderById(Integer orderId) {
        return orderRepository.deleteOrderById(orderId);
    }
}
