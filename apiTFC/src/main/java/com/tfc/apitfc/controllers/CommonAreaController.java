package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.dto.CommonAreaDTO;
import com.tfc.apitfc.domain.entity.CommonArea;
import com.tfc.apitfc.service.CommonAreaService;
import com.tfc.apitfc.service.NeighborService;
import com.tfc.apitfc.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private NeighborhoodService neighborhoodService;

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
    public void createCommonArea(@RequestBody CommonAreaDTO commonAreaDTO) {
        CommonArea commonArea = new CommonArea();
        commonArea.setName(commonAreaDTO.getName());
        commonArea.setNeighborhood(neighborhoodService.getNeighborhoodById(commonAreaDTO.getNeighborhoodId()));
        commonAreaService.save(commonArea);
    }

    @DeleteMapping("/{id}")
    public void deleteCommonArea(@PathVariable int id) {
        CommonArea commonArea = commonAreaService.getCommonAreaById(id);

        commonAreaService.delete(commonArea);
    }
}
