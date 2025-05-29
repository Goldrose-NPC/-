package com.example.classroom.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "`order`") // 使用反引号因为order是SQL关键字
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderId;  // 对应订单ID
    private Integer dishId;   // 对应菜品ID
    private Integer quantity; // 数量
    private BigDecimal price; // 单价

    @Column(name = "order_number", unique = true)
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "order_status")
    private Integer status;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> details = new ArrayList<>();

}
