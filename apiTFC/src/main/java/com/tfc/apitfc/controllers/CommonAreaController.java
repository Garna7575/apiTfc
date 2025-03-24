package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.entity.CommonArea;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.service.CommonAreaService;
import com.tfc.apitfc.service.NeighborService;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("pocket/common-area")
public class CommonAreaController {

    @Autowired
    private CommonAreaService commonAreaService;

    @Autowired
    private NeighborService neighborService;

    @GetMapping
    public ResponseEntity<List<CommonArea>> getAllCommonAreas() {
        List<CommonArea> commonAreas = commonAreaService.getAllCommonAreas();

        if (!commonAreas.isEmpty()) {
            return ResponseEntity.ok().body(commonAreas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommonArea>> getCommonAreaByNeighborhood(@PathVariable int id) {
        List<CommonArea> commonAreas = commonAreaService.getCommonAreaByNeighborhood(id);

        if (!commonAreas.isEmpty()) {
            return ResponseEntity.ok().body(commonAreas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{neighborhoodId}/{id}")
    public ResponseEntity<CommonArea> getCommonAreaById(@PathVariable int neighborhoodId, @PathVariable int id) {
        List<CommonArea> commonAreas = commonAreaService.getCommonAreaByNeighborhood(neighborhoodId);

        if (!commonAreas.isEmpty()) {
            Iterator<CommonArea> iterator = commonAreas.iterator();
            while (iterator.hasNext()) {
                CommonArea commonArea = iterator.next();
                if (commonArea.getId() == id) {
                    return ResponseEntity.ok().body(commonArea);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public void createCommonArea(@RequestBody CommonArea commonArea) {
        if (commonArea.getNeighbor() != null) {
            Neighbor neighbor = neighborService.findById(commonArea.getNeighbor().get(0).getId());
            neighbor.setCommonArea(commonArea);
        }

        commonAreaService.save(commonArea);
    }

    @DeleteMapping("/{id}")
    public void deleteCommonArea(@PathVariable int id) {
        CommonArea commonArea = commonAreaService.getCommonAreaById(id);

        if (commonArea.isBooked()) {
            commonArea.setBooked(false);
        }

        commonAreaService.delete(commonArea);
    }
}
