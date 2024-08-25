package com.ShoppingSite.controller.shopInterfaceController;

import com.ShoppingSite.model.shopInterface.Order;
import com.ShoppingSite.model.shopInterface.OrderStatus;
import com.ShoppingSite.service.shopInterfaceService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createOrder(@RequestBody Order order) {
        Integer orderId = orderService.createOrder(order);
        return ResponseEntity.ok(orderId);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Integer userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/updateStatus/{orderId}")
    public ResponseEntity<Integer> updateOrderStatus(@PathVariable Integer orderId, @RequestParam OrderStatus status) {
        Integer rowsUpdated = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(rowsUpdated);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Integer> deleteOrderById(@PathVariable Integer orderId) {
        Integer rowsDeleted = orderService.deleteOrderById(orderId);
        return ResponseEntity.ok(rowsDeleted);
    }
}
