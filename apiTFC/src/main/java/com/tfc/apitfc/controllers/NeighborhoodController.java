package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.entity.Neighborhood;
import com.tfc.apitfc.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pocket/neighborhood")
public class NeighborhoodController {

    @Autowired
    private NeighborhoodService neighborhoodService;

    @GetMapping
    public ResponseEntity<List<Neighborhood>> getAllNeighborhoods() {
        List<Neighborhood> neighborhoods = neighborhoodService.getAllNeighborhoods();

        if (!neighborhoods.isEmpty()) {
            return ResponseEntity.ok().body(neighborhoods);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Neighborhood> getNeighborhoodById(@PathVariable int id) {
        Neighborhood neighborhood = neighborhoodService.getNeighborhoodById(id);

        if (neighborhood != null) {
            return ResponseEntity.ok().body(neighborhood);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createNeighborhood(@RequestBody Neighborhood neighborhood) {
        neighborhoodService.addNeighborhood(neighborhood);
    }

    @DeleteMapping("/{id}")
    public void deleteNeighborhood(@PathVariable int id) {
        neighborhoodService.deleteNeighborhood(id);
    }
}
