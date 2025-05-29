package com.example.classroom.repository;

import com.example.classroom.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    List<ShoppingCart> findByUserId(Integer userId);
    Optional<ShoppingCart> findByUserIdAndDishId(Integer userId, Integer dishId);
    void deleteByUserId(Integer userId);
}
