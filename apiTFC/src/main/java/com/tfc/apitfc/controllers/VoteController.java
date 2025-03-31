package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.Vote;
import com.tfc.apitfc.service.NeighborService;
import com.tfc.apitfc.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pocket/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private NeighborService neighborService;

    @PostMapping("/{voteId}/{neighborId}/{inFavor}")
    public ResponseEntity<String> vote(@PathVariable int voteId, @PathVariable int neighborId, @PathVariable boolean inFavor) {
        Neighbor neighbor = neighborService.findById(neighborId);

        Vote vote = voteService.findById(voteId);

        String answer = voteService.castVote(neighbor, vote, inFavor);

        return ResponseEntity.ok().body(answer);
    }
}
