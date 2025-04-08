package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteInterface extends CrudRepository<Vote, Integer> {
    Vote findById(int id);

    List<Vote> findByNeighborhoodId(int neighborhoodId);

    @Query("SELECT v FROM Vote v WHERE v.neighborhood.id = :neighborhoodId")
    List<Vote> findVotesByNeighborhoodId(@Param("neighborhoodId") int neighborhoodId);
}
