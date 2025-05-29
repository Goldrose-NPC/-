package com.example.classroom.controller;

import com.example.classroom.entity.User;
import com.example.classroom.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Data;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestParam String userName,
            @RequestParam String password) {
        User user = userService.register(userName, password);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
        User user = userService.login(request.getUserName(), request.getPassword());
        return ResponseEntity.ok(user);
    }

    @Data
    public static class LoginRequest {
        private String userName;
        private String password;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}