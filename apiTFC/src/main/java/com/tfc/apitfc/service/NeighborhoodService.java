package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.NeighborInterface;
import com.tfc.apitfc.domain.dao.NeighborhoodInterface;
import com.tfc.apitfc.domain.entity.Neighborhood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeighborhoodService {
    @Autowired
    NeighborhoodInterface neighborhoodInterface;

    public List<Neighborhood> getAllNeighborhoods() {
        return neighborhoodInterface.findAll();
    }

    public Neighborhood getNeighborhoodById(int id) {
        return neighborhoodInterface.findById(id);
    }

    public void addNeighborhood(Neighborhood neighborhood) {
        neighborhoodInterface.save(neighborhood);
    }

    public void deleteNeighborhood(int id) {
        neighborhoodInterface.deleteById(id);
    }
}
