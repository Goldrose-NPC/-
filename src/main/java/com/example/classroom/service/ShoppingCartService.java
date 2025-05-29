package com.example.classroom.service;

import com.example.classroom.entity.ShoppingCart;
import java.util.List;

public interface ShoppingCartService {
    ShoppingCart addToCart(Integer userId, Integer dishId, Integer quantity);
    List<ShoppingCart> getCartItems(Integer userId);
    void removeFromCart(Integer cartItemId);
    void clearCart(Integer userId);
}
