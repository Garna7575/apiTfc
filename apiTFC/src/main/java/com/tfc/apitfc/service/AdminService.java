package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.AdminInterface;
import com.tfc.apitfc.domain.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminInterface adminInterface;

    public List<Admin> getAllAdmins() {
        return adminInterface.findAll();
    }

    public Admin getAdminById(int id) {
        return adminInterface.findById(id);
    }

    public Admin getAdminByUserId(int userId) {
        return adminInterface.findByUserId(userId);
    }

    public void deleteAdminByRegistrationNumber(String registrationNumber) {
        Admin admin = adminInterface.findByRegistrationNumber(registrationNumber);

        if (admin != null) {
            adminInterface.delete(admin);
        }
    }

    public void addAdmin(Admin admin) {
        adminInterface.save(admin);
    }
}
