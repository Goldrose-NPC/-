package com.example.classroom.service.impl;

import com.example.classroom.entity.*;
import com.example.classroom.repository.*;
import com.example.classroom.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository cartRepository;
    private final DishRepository dishRepository;

    // 显式构造函数
    public ShoppingCartServiceImpl(
            ShoppingCartRepository cartRepository,
            DishRepository dishRepository
    ) {
        this.cartRepository = cartRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public ShoppingCart addToCart(Integer userId, Integer dishId, Integer quantity) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new RuntimeException("菜品不存在"));

        ShoppingCart cartItem = cartRepository.findByUserIdAndDishId(userId, dishId)
                .orElse(new ShoppingCart());

        cartItem.setUser(new User(userId));
        cartItem.setDish(dish);
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
//        cartItem.setPrice(dish.getDishPrice()); // 假设Dish的价格字段是dishPrice

        return cartRepository.save(cartItem);
    }

    @Override
    public List<ShoppingCart> getCartItems(Integer userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void removeFromCart(Integer cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    @Override
    public void clearCart(Integer userId) {
        cartRepository.deleteByUserId(userId);
    }
}