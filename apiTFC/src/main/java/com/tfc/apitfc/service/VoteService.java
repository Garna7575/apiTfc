package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.NeighborInterface;
import com.tfc.apitfc.domain.dao.NeighborVoteInterface;
import com.tfc.apitfc.domain.dao.VoteInterface;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.NeighborVote;
import com.tfc.apitfc.domain.entity.Vote;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    @Autowired
    VoteInterface voteInterface;

    @Autowired
    NeighborVoteInterface neighborVoteInterface;

    @Autowired
    private NeighborInterface neighborInterface;

    public Vote findById(int id) {
        return voteInterface.findById(id);
    }

    public List<Vote> findByNeighborhoodId(int neighborhoodId) {
        return voteInterface.findByNeighborhoodId(neighborhoodId);
    }

    public List<Vote> getVotesByUserId(int userId) {
        Neighbor neighbor = neighborInterface.findByUserId(userId);
        if (neighbor == null || neighbor.getNeighborhood() == null) {
            throw new RuntimeException("El usuario no pertenece a un vecindario");
        }
        return voteInterface.findVotesByNeighborhoodId(neighbor.getNeighborhood().getId());
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

    public void saveVote(Vote vote){
        voteInterface.save(vote);
    }

    public void deleteVote(int id){
        voteInterface.deleteById(id);
    }
}
