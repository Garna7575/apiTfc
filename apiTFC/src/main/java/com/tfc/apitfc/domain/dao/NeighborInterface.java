package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.Neighborhood;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeighborInterface extends CrudRepository<Neighbor, Integer> {
    List<Neighbor> findAll();

    List<Neighbor> findByNeighborhood(Neighborhood neighborhood);

    Neighbor findById(int id);

    void deleteById(int id);
}
