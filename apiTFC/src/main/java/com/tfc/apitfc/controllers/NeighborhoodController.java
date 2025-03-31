package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.entity.Neighborhood;
import com.tfc.apitfc.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Map;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNeighborhoodWithBase64(@RequestBody Neighborhood neighborhood) {
        try {
            if (neighborhood.getBase64Image() != null && !neighborhood.getBase64Image().isEmpty()) {
                String base64Data = neighborhood.getBase64Image();

                if (base64Data.contains(",")) {
                    base64Data = base64Data.split(",")[1];
                }

                byte[] imageBytes = Base64.getDecoder().decode(
                        base64Data.replaceAll("\\s+", "")
                                .replaceAll("[^A-Za-z0-9+/=]", "")
                );
                neighborhood.setImage(imageBytes);
            }

            neighborhoodService.addNeighborhood(neighborhood);
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Barrio creado exitosamente"
            ));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Formato Base64 inválido",
                    "details", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "error",
                    "message", "Error al crear el barrio",
                    "details", e.getMessage()
            ));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteNeighborhood(@PathVariable int id) {
        neighborhoodService.deleteNeighborhood(id);
    }
}
