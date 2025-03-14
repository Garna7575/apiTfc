package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.entity.Users;
import com.tfc.apitfc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pocket/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();

        if (!users.isEmpty()) {
            return ResponseEntity.ok().body(users);
        } else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public void createUser(@Valid @RequestBody Users user) {
        userService.saveUser(user);
    }
}
