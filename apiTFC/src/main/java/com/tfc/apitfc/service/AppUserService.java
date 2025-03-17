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

    public List<AppUser> getAllUsers() {
        return appUserInterface.findAll();
    }

    public AppUser getUserById(int id) {
        return appUserInterface.findById(id);
    }

    public void addUser(AppUser appUser) {
        appUserInterface.save(appUser);
    }

    public void deleteUser(AppUser appUser) {
        appUserInterface.delete(appUser);
    }
}
