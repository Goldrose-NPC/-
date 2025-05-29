package com.example.classroom.service;

import com.example.classroom.entity.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Integer userId);
    Order getOrderById(Integer id);
    List<Order> getOrdersByUser(Integer userId);
}