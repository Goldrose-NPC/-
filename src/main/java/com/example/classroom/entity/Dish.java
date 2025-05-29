package com.example.classroom.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dish_name", nullable = false)
    private String dishName;

    @Column(name = "dish_price", nullable = false)
    private BigDecimal dishPrice;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private DishCategory category;

    @Column(name = "dish_image")
    private String dishImage;

    @Column(name = "dish_desc")
    private String dishDesc;

    @Column(name = "dish_status", nullable = false)
    private Integer dishStatus = 1; // 1上架, 0下架


}
