package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.AppUserInterface;
import com.tfc.apitfc.domain.entity.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {
    @Autowired
    AppUserInterface appUserInterface;
    @Autowired
    private PasswordHashService passwordHashService;
    @Autowired
    private MailService mailService;

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

    @Transactional
    public void changePassword(int id, String currentPassword, String newPassword) {
        AppUser user = appUserInterface.findById(id);

        if (!isPasswordValid(newPassword)) {
            throw new IllegalArgumentException("La nueva contraseña no cumple con los requisitos de seguridad");
        }

        user.setPassword(passwordHashService.hashPassword(newPassword));
        appUserInterface.save(user);
    }

    @Transactional
    public boolean forgotPassword(String email) throws Exception {
        AppUser user = appUserInterface.findByEmail(email);

        if (user != null) {
            mailService.sendEmail(email, "Recuperación de correo", "hola :D");
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[!@#$%^&*].*");
    }

    public void updateUser(AppUser appUser) {
        appUserInterface.save(appUser);
    }

    public void deleteUser(AppUser appUser) {
        appUserInterface.delete(appUser);
    }
}
