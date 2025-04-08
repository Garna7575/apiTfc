package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.Neighborhood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeighborInterface extends CrudRepository<Neighbor, Integer> {
    List<Neighbor> findAll();

    List<Neighbor> findByNeighborhood(Neighborhood neighborhood);

    @Query("SELECT n.neighborhood.id FROM Neighbor n WHERE n.user.id = :userId")
    Integer findNeighborhoodIdByUserId(@Param("userId") int userId);

    Neighbor findById(int id);

    Neighbor findByUserId(int id);

    void deleteById(int id);
}
