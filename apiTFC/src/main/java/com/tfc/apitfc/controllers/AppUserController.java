package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.entity.AppUser;
import com.tfc.apitfc.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pocket/users")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @GetMapping
    public ResponseEntity<List<AppUser>> findAll() {
        List<AppUser> users = appUserService.getAllUsers();

        if (!users.isEmpty()){
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> findById(@PathVariable int id) {
        AppUser user = appUserService.getUserById(id);

        if (user != null){
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createUser(@RequestBody AppUser user) {
        if (user != null) {
            appUserService.addUser(user);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        AppUser user = appUserService.getUserById(id);

        if (user != null) {
            appUserService.deleteUser(user);
        }
    }
}
