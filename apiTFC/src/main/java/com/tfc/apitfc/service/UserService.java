package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.UserInteface;
import com.tfc.apitfc.domain.entity.Users;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserInteface userInteface;

    public List<Users> getAllUsers() {
        return userInteface.findAll();
    }

    public void saveUser(Users user) {
        try {
            System.out.println(user);
            userInteface.save(user);
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("Error: Campos obligatorios no pueden ser nulos", e);
        }
    }
}
