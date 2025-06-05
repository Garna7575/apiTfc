package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.entity.Admin;
import com.tfc.apitfc.domain.entity.Neighborhood;
import com.tfc.apitfc.domain.dto.NeighborhoodDTO;
import com.tfc.apitfc.domain.mapper.NeighborhoodMapper;
import com.tfc.apitfc.service.NeighborhoodService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pocket/neighborhood")
public class NeighborhoodController {

    @Autowired
    private NeighborhoodService neighborhoodService;

    @GetMapping
    @Transactional
    public ResponseEntity<List<NeighborhoodDTO>> getAllNeighborhoods() {
        List<Neighborhood> neighborhoods = neighborhoodService.getAllNeighborhoods();

        if (!neighborhoods.isEmpty()) {
            List<NeighborhoodDTO> neighborhoodDTOs = neighborhoods.stream()
                    .map(NeighborhoodMapper::toDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(neighborhoodDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<NeighborhoodDTO> getNeighborhoodById(@PathVariable int id) {
        Neighborhood neighborhood = neighborhoodService.getNeighborhoodById(id);

        if (neighborhood != null) {
            NeighborhoodDTO dto = NeighborhoodMapper.toDTO(neighborhood);
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("admin/{id}")
    @Transactional
    public ResponseEntity<List<NeighborhoodDTO>> getNeighborhoodByAdminId(@PathVariable int id) {
        List<Neighborhood> neighborhoods = neighborhoodService.getNeighborhoodByAdminId(id);

        if (neighborhoods != null && !neighborhoods.isEmpty()) {
            List<NeighborhoodDTO> neighborhoodDTOS = new ArrayList<>();
            for (Neighborhood neighborhood : neighborhoods) {
                NeighborhoodDTO dto = NeighborhoodMapper.toDTO(neighborhood);
                neighborhoodDTOS.add(dto);
            }

            return ResponseEntity.ok().body(neighborhoodDTOS);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/admin")
    @Transactional
    public ResponseEntity<Admin> getAdminByNeighborhood(@PathVariable int id) {
        Admin admin = neighborhoodService.getAdminByNeighborhood(id);

        if (admin != null) {
            return ResponseEntity.ok().body(admin);
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
