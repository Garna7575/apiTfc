package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminInterface extends CrudRepository<Admin, Integer> {
    List<Admin> findAll();

    Admin findByRegistrationNumber(String registrationNumber);

    Admin findByUserId(int userId);

    Admin findById(int id);

    void deleteById(Integer id);
}
