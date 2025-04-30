package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserInterface extends CrudRepository<AppUser, Integer> {
    List<AppUser> findAll();

    AppUser findById(int id);

    AppUser findByUsername(String username);

    AppUser findByUsernameAndPassword(String username, String password);

    AppUser findByEmail(String email);

    void deleteById(int id);
}
