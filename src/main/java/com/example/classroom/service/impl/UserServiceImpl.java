package com.example.classroom.service.impl;

import com.example.classroom.entity.User;
import com.example.classroom.repository.UserRepository;
import com.example.classroom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    User getUserById(Integer id);

    @Override  // 添加@Override注解确保正确实现
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
    }

    @Override
    public User register(String userName, String password) {
        if (userRepository.existsByUserName(userName)) {
            throw new RuntimeException("用户名已存在");
        }

        User user = new User();
        user.setUserName(userName);
//        user.setUserPass(passwordEncoder.encode(password));
        user.setUserPass(password);
        return userRepository.save(user);
    }

    @Override
    public User login(String userName, String password) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

//        if (!passwordEncoder.matches(password, user.getUserPass())) {
//            throw new RuntimeException("密码错误");
//        }
        if (!password.equals(user.getUserPass())) {
            throw new RuntimeException("密码错误");
        }

        return user;
    }
}