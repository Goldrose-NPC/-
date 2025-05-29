package com.example.classroom.repository;

import com.example.classroom.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(Integer userId);
    Optional<Order> findByOrderNumber(String orderNumber);
}