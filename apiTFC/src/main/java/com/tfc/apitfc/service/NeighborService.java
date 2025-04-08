package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.NeighborInterface;
import com.tfc.apitfc.domain.dao.NeighborhoodInterface;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.Neighborhood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeighborService {
    @Autowired
    NeighborInterface neighborInterface;

    @Autowired
    NeighborhoodInterface neighborhoodInterface;

    public List<Neighbor> findAllNeighbors(){
        return neighborInterface.findAll();
    }

    public List<Neighbor> findNeighborsByNeighborhood(int id){
        Neighborhood neighborhood = neighborhoodInterface.findById(id);
        if (neighborhood != null) {
            return neighborInterface.findByNeighborhood(neighborhood);
        } else{
            return null;
        }
    }

    public Integer getNeighborhoodIdByUserId(int userId) {
        return neighborInterface.findNeighborhoodIdByUserId(userId);
    }

    public Neighbor findById(int id){
        return neighborInterface.findById(id);
    }

    public Neighbor findByUserId(int id){
        return neighborInterface.findByUserId(id);
    }

    public Neighbor createNeighbor(Neighbor neighbor){
        return neighborInterface.save(neighbor);
    }

    public void deleteNeighbor(int id){
        neighborInterface.deleteById(id);
    }
}
