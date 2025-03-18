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

    public Admin getAdminByRegistrationNumber(String registrationNumber) {
        return adminInterface.findByRegistrationNumber(registrationNumber);
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
