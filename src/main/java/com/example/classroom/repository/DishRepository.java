package com.example.classroom.repository;

import com.example.classroom.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> findByCategoryId(Integer categoryId);
    List<Dish> findByDishNameContaining(String keyword);
    List<Dish> findByDishStatus(Integer status);
    
}
