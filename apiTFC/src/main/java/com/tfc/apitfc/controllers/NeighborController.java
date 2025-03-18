package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.service.NeighborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pocket/neighbor")
public class NeighborController {

    @Autowired
    NeighborService neighborService;

    @GetMapping
    public ResponseEntity<List<Neighbor>> getNeighbors() {
        List<Neighbor> neighbors = neighborService.findAllNeighbors();

        if (!neighbors.isEmpty()) {
            return ResponseEntity.ok().body(neighbors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{neighborhoodId}")
    public ResponseEntity<List<Neighbor>> getNeighborsByNeighborhood(@PathVariable int neighborhoodId) {
        List<Neighbor> neighbors = neighborService.findNeighborsByNeighborhood(neighborhoodId);

        if (!neighbors.isEmpty()) {
            return ResponseEntity.ok().body(neighbors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createNeighbor(@RequestBody Neighbor neighbor) {
        neighborService.createNeighbor(neighbor);
    }

    @DeleteMapping("/{id}")
    public void deleteNeighbor(@PathVariable int id) {
        neighborService.deleteNeighbor(id);
    }
}
