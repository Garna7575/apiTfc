package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.NeighborInterface;
import com.tfc.apitfc.domain.dao.NeighborhoodInterface;
import com.tfc.apitfc.domain.dto.NeighborDTO;
import com.tfc.apitfc.domain.dto.UserDTO;
import com.tfc.apitfc.domain.entity.AppUser;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.Neighborhood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<NeighborDTO> getNeighborsByNeighborhood(Integer neighborhoodId) {
        List<Neighbor> neighbors = neighborInterface.findByNeighborhoodId(neighborhoodId);
        return neighbors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
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

    private NeighborDTO convertToDto(Neighbor neighbor) {
        NeighborDTO dto = new NeighborDTO();
        dto.setId(neighbor.getId());
        dto.setHouse(neighbor.getHouse());

        UserDTO userDto = new UserDTO();
        AppUser user = neighbor.getUser();
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setTlphNumber(user.getTlphNumber());

        dto.setUser(userDto);
        return dto;
    }
}
