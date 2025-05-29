package com.example.classroom.controller;

import com.example.classroom.entity.Dish;
import com.example.classroom.repository.DishRepository;
import com.example.classroom.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/{id}")
    public Dish getDishById(@PathVariable Integer id) {
        return dishService.getDishById(id);
    }

    @PostMapping
    public Dish createDish(@RequestBody Dish dish) {
        return dishService.createDish(dish);
    }
}