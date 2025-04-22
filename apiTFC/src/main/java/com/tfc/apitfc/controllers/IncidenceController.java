package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.dao.IncidencesInterface;
import com.tfc.apitfc.domain.dto.IncidenceDTO;
import com.tfc.apitfc.domain.entity.Incidence;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.service.NeighborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tfc.apitfc.service.IncidenceService;

import java.util.List;

@RestController
@RequestMapping("pocket/incidences")
public class IncidenceController {
    @Autowired
    IncidenceService incidenceService;
    @Autowired
    private NeighborService neighborService;

    @GetMapping
    public ResponseEntity<List<Incidence>> getAllIncidences() {
        List<Incidence> incidences = incidenceService.findAllIncidences();

        if (!incidences.isEmpty()) {
            return ResponseEntity.ok().body(incidences);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{neighborhoodId}")
    public ResponseEntity<List<Incidence>> getIncidenceByNeighborhoodId(@PathVariable int neighborhoodId) {
        List<Incidence> incidences = incidenceService.findIncidencesByNeighborhoodId(neighborhoodId);

        if (!incidences.isEmpty()) {
            return ResponseEntity.ok().body(incidences);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/{neighborId}")
    public void createIncidence(@RequestBody IncidenceDTO incidenceDTO, @PathVariable int neighborId) {
        Neighbor neighbor = neighborService.findById(neighborId);

        Incidence incidence = new Incidence();
        incidence.setTitle(incidenceDTO.getTitle());
        incidence.setContent(incidenceDTO.getContent());
        incidence.setDate(incidenceDTO.getDate());
        incidence.setStatus(0);
        incidence.setPriority(incidenceDTO.getPriority());

        incidence.setNeighbor(neighbor);
        incidence.setNeighborhood(neighbor.getNeighborhood());

        incidenceService.save(incidence);

    }
}
