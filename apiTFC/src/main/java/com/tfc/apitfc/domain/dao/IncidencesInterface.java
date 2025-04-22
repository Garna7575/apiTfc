package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Incidence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidencesInterface extends CrudRepository<Incidence, Integer> {
    List<Incidence> findAll();

    List<Incidence> findIncidenceByNeighborhoodIdOrderByIdDesc(int neighborhoodId);
}
