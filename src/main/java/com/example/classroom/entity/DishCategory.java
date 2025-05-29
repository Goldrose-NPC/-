package com.example.classroom.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dish_category")
public class DishCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;
}
