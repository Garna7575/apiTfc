package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.AppUserInterface;
import com.tfc.apitfc.domain.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {
    @Autowired
    AppUserInterface appUserInterface;
    @Autowired
    private PasswordHashService passwordHashService;

    public List<AppUser> getAllUsers() {
        return appUserInterface.findAll();
    }

    public AppUser getUserById(int id) {
        return appUserInterface.findById(id);
    }

    public AppUser getUserByUsername(String username) {
        return appUserInterface.findByUsername(username);
    }

    public AppUser getUserByUsernameAndPassword(String username, String password) {
        return appUserInterface.findByUsernameAndPassword(username, password);
    }

    public AppUser addUser(AppUser appUser) {
        appUser.setPassword(passwordHashService.hashPassword(appUser.getPassword()));
        return appUserInterface.save(appUser);
    }

    public void deleteUser(AppUser appUser) {
        appUserInterface.delete(appUser);
    }
}
