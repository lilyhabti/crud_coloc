package com.coloc.crud.coloc.controllers;

import com.coloc.crud.coloc.models.User;
import com.coloc.crud.coloc.services.UserService;
import com.coloc.crud.coloc.services.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping
    public ResponseEntity<User> createOrUpdateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveOrUpdateUser(user));
    }
}