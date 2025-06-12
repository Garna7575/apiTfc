package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.NeighborVoteInterface;
import com.tfc.apitfc.domain.dto.VotingStatus;
import com.tfc.apitfc.domain.entity.NeighborVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NeighborVoteService {
    @Autowired
    NeighborVoteInterface neighborVoteInterface;

    public VotingStatus getStatus(int voteId){
        List<NeighborVote> votes = neighborVoteInterface.findByVoteId(voteId);
        VotingStatus votingStatus = new VotingStatus();

        for (NeighborVote vote : votes) {
            if (vote.isInFavor()){
                votingStatus.addInFavor();
            } else {
                votingStatus.addAgainst();
            }
        }
        votingStatus.setTotal(votes.size());

        return votingStatus;
    }

    public Optional<NeighborVote> findByNeighborAndVoteId(int neighborId, int voteId){
        return neighborVoteInterface.findByNeighborIdAndVoteId(neighborId, voteId);
    }

    public void save(NeighborVote neighborVote){
        neighborVoteInterface.save(neighborVote);
    }
}
