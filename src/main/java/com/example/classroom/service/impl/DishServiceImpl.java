package com.example.classroom.service.impl;

import com.example.classroom.entity.Dish;
import com.example.classroom.exception.BusinessException;
import com.example.classroom.repository.DishRepository;
import com.example.classroom.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DishServiceImpl implements DishService {

    @Override
    public Dish getDishById(Integer id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new BusinessException("菜品不存在"));
    }

    @Override
    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Autowired
    private DishRepository dishRepository;

    @Override
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    @Override
    public List<Dish> searchDishes(String keyword) {
        return dishRepository.findByDishNameContaining(keyword);
    }

    @Override
    public List<Dish> getDishesByCategory(Integer categoryId) {
        return dishRepository.findByCategoryId(categoryId);
    }
}