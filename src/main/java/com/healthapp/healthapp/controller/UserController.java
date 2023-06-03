package com.healthapp.healthapp.controller;

import com.healthapp.healthapp.entity.User;
import com.healthapp.healthapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return userService.login(user); //авторизация
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return userService.createUser(user); //регистрация
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user); //обновление данных пользователя
    }

    @GetMapping("/doctors")
    public ResponseEntity<?> getDoctors(@RequestParam(required = false) String fts) { //параметр необязательный,только если что-то введём в строке поиска
        return userService.getDoctors(fts); //получение списка врачей отфильтрованных по FTS (полнотекстовый поиск)
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String fts) {
        return userService.getUsers(fts); //получение списка пользователей отфильтрованных по FTS
    }

    @GetMapping
    public ResponseEntity<?> currentUser(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getById(id)); //получение текущего пользователя, очень часто нужен на фронте
    }
}
