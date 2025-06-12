package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.dto.VotingStatus;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.NeighborVote;
import com.tfc.apitfc.domain.entity.Neighborhood;
import com.tfc.apitfc.domain.entity.Vote;
import com.tfc.apitfc.service.MailService;
import com.tfc.apitfc.service.NeighborService;
import com.tfc.apitfc.service.NeighborVoteService;
import com.tfc.apitfc.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pocket/votes")
public class NeighborVoteController {
    @Autowired
    NeighborVoteService neighborVoteService;

    @Autowired
    NeighborService neighborService;

    @Autowired
    VoteService voteService;
    @Autowired
    private MailService mailService;

    @GetMapping("/{voteId}")
    public ResponseEntity<VotingStatus> getStatus(@PathVariable int voteId){
        return ResponseEntity.ok(neighborVoteService.getStatus(voteId));
    }


    @PostMapping("/vote/{neighborId}/{voteId}/{inFavor}")
    public ResponseEntity<String> vote(@PathVariable int neighborId, @PathVariable int voteId, @PathVariable boolean inFavor) {
        Optional<NeighborVote> existingVote = neighborVoteService.findByNeighborAndVoteId(neighborId, voteId);

        if (existingVote.isPresent()) {
            return ResponseEntity.ok().body("No puedes cambiar tu voto.");
        }

        Neighbor neighbor = neighborService.findById(neighborId);
        Vote vote = voteService.findById(voteId);

        if (neighbor == null || vote == null) {
            return ResponseEntity.ok().body("Vecino o voto no encontrado.");
        }

        NeighborVote newVote = new NeighborVote();
        newVote.setNeighbor(neighbor);
        newVote.setVote(vote);
        newVote.setInFavor(inFavor);
        neighborVoteService.save(newVote);

        return ResponseEntity.ok("Voto registrado correctamente.");
    }

    @PostMapping("end_voting/{voteId}")
    public void endVoting(@PathVariable int voteId) throws Exception {
        Vote vote = voteService.findById(voteId);
        VotingStatus votingStatus = neighborVoteService.getStatus(voteId);
        Neighborhood neighborhood = vote.getNeighborhood();

        boolean isApproved = votingStatus.getInFavor() > votingStatus.getAgainst();

        for (Neighbor neighbor : neighborhood.getNeighbors()){
            mailService.sendVotingResultEmail(neighbor.getUser().getEmail(), neighbor.getUser().getName(), vote.getTitle(), isApproved);
        }

        voteService.deleteVote(voteId);
    }
}
