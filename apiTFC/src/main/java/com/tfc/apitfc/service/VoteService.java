package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.NeighborVoteInterface;
import com.tfc.apitfc.domain.dao.VoteInterface;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.NeighborVote;
import com.tfc.apitfc.domain.entity.Vote;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    @Autowired
    VoteInterface voteInterface;

    @Autowired
    NeighborVoteInterface neighborVoteInterface;

    public Vote findById(int id) {
        return voteInterface.findById(id);
    }

    @Transactional
    public String castVote(Neighbor neighbor, Vote vote, boolean inFavor) {

        NeighborVote neighborVote = new NeighborVote();
        neighborVote.setNeighbor(neighbor);
        neighborVote.setVote(vote);
        neighborVote.setInFavor(inFavor);
        neighborVoteInterface.save(neighborVote);

        voteInterface.save(vote);

        return "Voto registrado";
    }
}
