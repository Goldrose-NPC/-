package com.example.classroom.repository;

import com.example.classroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 通过用户名查找用户
    Optional<User> findByUserName(String userName);

    // 通过手机号查找用户（用于注册校验）
    Optional<User> findByUserPhone(String userPhone);

    // 检查用户名是否已存在
    boolean existsByUserName(String userName);

    // 检查手机号是否已存在
    boolean existsByUserPhone(String userPhone);
    User findByUserId(String userId);
//    User findByUserPhone(String phone);
    User findByWechatOpenid(String openid);
}
