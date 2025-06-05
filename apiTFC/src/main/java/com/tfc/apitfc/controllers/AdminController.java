package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.entity.Admin;
import com.tfc.apitfc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pocket/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();

        if (!admins.isEmpty()) {
            return ResponseEntity.ok().body(admins);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
        Admin admin = adminService.getAdminById(id);

        if (admin != null) {
            return ResponseEntity.ok().body(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("userId/{id}")
    public ResponseEntity<Admin> getAdminByUserId(@PathVariable int id) {
        Admin admin = adminService.getAdminByUserId(id);

        if (admin != null) {
            return ResponseEntity.ok().body(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createAdmin(@RequestBody Admin admin) {
        if (admin != null) {
            adminService.addAdmin(admin);
        }
    }

    @DeleteMapping("/{registrationNumber}")
    public void deleteAdminByRegistrationNumber(@PathVariable String registrationNumber) {
        adminService.deleteAdminByRegistrationNumber(registrationNumber);
    }
}
