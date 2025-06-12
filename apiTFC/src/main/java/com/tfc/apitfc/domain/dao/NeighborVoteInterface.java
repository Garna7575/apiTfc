package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.NeighborVote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NeighborVoteInterface extends CrudRepository<NeighborVote, Integer> {
    Optional<NeighborVote> findByNeighborIdAndVoteId(int neighborId, int voteId);

    List<NeighborVote> findByVoteId(int voteId);
}
