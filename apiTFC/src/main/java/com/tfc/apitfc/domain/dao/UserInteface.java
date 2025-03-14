package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInteface extends CrudRepository<Users, Integer> {
    List<Users> findAll();
}
