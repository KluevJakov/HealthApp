package com.healthapp.healthapp.controller;

import com.healthapp.healthapp.entity.User;
import com.healthapp.healthapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/doctors")
    public ResponseEntity<?> getDoctors() {
        return userService.getDoctors();
    }

    @GetMapping
    public ResponseEntity<?> currentUser(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }
}
