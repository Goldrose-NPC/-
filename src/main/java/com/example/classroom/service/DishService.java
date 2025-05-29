package com.example.classroom.service;

import com.example.classroom.entity.Dish;
import java.util.List;

public interface DishService {
    List<Dish> getAllDishes();
    List<Dish> searchDishes(String keyword);
    List<Dish> getDishesByCategory(Integer categoryId);
    Dish getDishById(Integer id);
    Dish createDish(Dish dish);
}
