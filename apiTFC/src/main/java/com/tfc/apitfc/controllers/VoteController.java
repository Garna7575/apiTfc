package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.NeighborVote;
import com.tfc.apitfc.domain.entity.Vote;
import com.tfc.apitfc.service.NeighborService;
import com.tfc.apitfc.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pocket/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private NeighborService neighborService;

    @GetMapping("/{neighborhoodId}")
    public ResponseEntity<List<Vote>> getVotesByNeighborhoodId(@PathVariable int neighborhoodId) {
        List<Vote> votes = voteService.findByNeighborhoodId(neighborhoodId);

        if (!votes.isEmpty()) {
            return ResponseEntity.ok().body(votes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Vote>> getVotesByUserId(@PathVariable int userId) {
        List<Vote> votes = voteService.getVotesByUserId(userId);
        return ResponseEntity.ok().body(votes);
    }

    @PostMapping("/{voteId}/{neighborId}/{inFavor}")
    public ResponseEntity<String> vote(@PathVariable int voteId, @PathVariable int neighborId, @PathVariable boolean inFavor) {
        Neighbor neighbor = neighborService.findById(neighborId);

        Vote vote = voteService.findById(voteId);

        String answer = voteService.castVote(neighbor, vote, inFavor);

        return ResponseEntity.ok().body(answer);
    }
}
