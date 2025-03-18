package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Neighborhood;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeighborhoodInterface extends CrudRepository<Neighborhood, Integer> {

    List<Neighborhood> findAll();

    Neighborhood findById(int id);

    Neighborhood findByName(String name);

    void deleteById(int id);
}
