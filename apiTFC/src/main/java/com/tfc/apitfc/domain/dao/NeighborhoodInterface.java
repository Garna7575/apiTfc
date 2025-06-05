package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Admin;
import com.tfc.apitfc.domain.entity.Neighborhood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeighborhoodInterface extends CrudRepository<Neighborhood, Integer> {

    List<Neighborhood> findAll();

    Neighborhood findById(int id);

    List<Neighborhood> findByAdminId(int adminId);

    @Query("SELECT n.admin FROM Neighborhood n WHERE n.id = :neighborhoodId")
    Admin findAdminByNeighborhoodId(@Param("neighborhoodId") int neighborhoodId);

    void deleteById(int id);
}
