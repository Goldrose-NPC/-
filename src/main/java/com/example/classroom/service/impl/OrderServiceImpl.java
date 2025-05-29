package com.example.classroom.service.impl;

import com.example.classroom.entity.*;
import com.example.classroom.exception.BusinessException;
import com.example.classroom.repository.*;
import com.example.classroom.service.OrderService;
import com.example.classroom.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShoppingCartService cartService;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ShoppingCartService cartService,
                            UserRepository userRepository,
                            OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<Order> getOrdersByUser(Integer userId) {
        return orderRepository.findByUserId(userId); // 确保返回集合
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new BusinessException("订单不存在"));
    }

    @Override
    public Order createOrder(Integer userId) {
        // 1. 先查询用户是否存在
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在")); // ✅ 确保用户有效

        // 2. 获取购物车项（原逻辑不变）
        List<ShoppingCart> cartItems = cartService.getCartItems(userId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("购物车为空");
        }

        // 3. 创建订单并关联用户
        Order order = new Order();
        order.setUser(user); // ✅ 使用查询到的完整用户对象
        order.setOrderNumber(generateOrderNumber());
        order.setStatus(0);
        order.setCreateTime(LocalDateTime.now());

        // 4. 计算总金额（原逻辑不变）
        BigDecimal totalAmount = cartItems.stream()
                .map(item -> item.getDish().getDishPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setTotalAmount(totalAmount);

        // 5. 保存订单和详情（原逻辑不变）
        Order savedOrder = orderRepository.save(order);
        cartItems.forEach(item -> {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);
            detail.setDish(item.getDish());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        });

        // 6. 清空购物车（原逻辑不变）
        cartService.clearCart(userId);
        return savedOrder;
    }

    private String generateOrderNumber() {
        return "ORD" + System.currentTimeMillis();
    }
}