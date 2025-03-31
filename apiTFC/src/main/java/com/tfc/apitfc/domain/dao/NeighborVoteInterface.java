package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.NeighborVote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighborVoteInterface extends CrudRepository<NeighborVote, Integer> {
    boolean existsByNeighborIdAndVoteId(Integer neighborId, Integer neighborVoteId);
}
