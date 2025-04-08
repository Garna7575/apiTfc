package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.dao.NeighborInterface;
import com.tfc.apitfc.domain.dao.NeighborVoteInterface;
import com.tfc.apitfc.domain.dao.VoteInterface;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.NeighborVote;
import com.tfc.apitfc.domain.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pocket/votes")
public class NeighborVoteController {
    @Autowired
    NeighborVoteInterface neighborVoteInterface;

    @Autowired
    NeighborInterface neighborInterface;

    @Autowired
    VoteInterface voteInterface;


    @PostMapping("/vote/{neighborId}/{voteId}/{inFavor}")
    public ResponseEntity<String> vote(@PathVariable int neighborId, @PathVariable int voteId, @PathVariable boolean inFavor) {
        Optional<NeighborVote> existingVote = neighborVoteInterface.findByNeighborIdAndVoteId(neighborId, voteId);

        if (existingVote.isPresent()) {
            return ResponseEntity.ok().body("No puedes cambiar tu voto.");
        }

        Neighbor neighbor = neighborInterface.findById(neighborId);
        Vote vote = voteInterface.findById(voteId);

        if (neighbor == null || vote == null) {
            return ResponseEntity.ok().body("Vecino o voto no encontrado.");
        }

        NeighborVote newVote = new NeighborVote();
        newVote.setNeighbor(neighbor);
        newVote.setVote(vote);
        newVote.setInFavor(inFavor);
        neighborVoteInterface.save(newVote);

        return ResponseEntity.ok("Voto registrado correctamente.");
    }
}
