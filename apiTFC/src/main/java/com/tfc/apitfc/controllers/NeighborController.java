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

    @GetMapping("/{userId}")
    public ResponseEntity<Neighbor> getNeighborByUserId(@PathVariable("userId") int userId) {
        Neighbor neighbor = neighborService.findByUserId(userId);

        if (neighbor != null) {
            return ResponseEntity.ok().body(neighbor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/neighborhood-id/{userId}")
    public ResponseEntity<Integer> getNeighborhoodId(@PathVariable int userId) {
        Integer neighborhoodId = neighborService.getNeighborhoodIdByUserId(userId);
        return ResponseEntity.ok(neighborhoodId);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Neighbor> getNeighborById(@PathVariable int id) {
//        Neighbor neighbor = neighborService.findByUserId(id);
//
//        if (neighbor != null) {
//            return ResponseEntity.ok().body(neighbor);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping
    public void createNeighbor(@RequestBody Neighbor neighbor) {
        neighborService.createNeighbor(neighbor);
    }

    @DeleteMapping("/{id}")
    public void deleteNeighbor(@PathVariable int id) {
        neighborService.deleteNeighbor(id);
    }
}
