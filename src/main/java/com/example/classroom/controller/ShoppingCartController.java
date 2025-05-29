package com.example.classroom.controller;

import com.example.classroom.entity.ShoppingCart;
import com.example.classroom.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private final ShoppingCartService cartService;

    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public List<ShoppingCart> getCartItems(@PathVariable Integer userId) {
        return cartService.getCartItems(userId);
    }

    @PostMapping("/add")
    public ShoppingCart addToCart(
            @RequestParam Integer userId,
            @RequestParam Integer dishId,
            @RequestParam Integer quantity) {
        return cartService.addToCart(userId, dishId, quantity);
    }

    @DeleteMapping("/{itemId}")
    public void removeFromCart(@PathVariable Integer itemId) {
        cartService.removeFromCart(itemId);
    }
}