package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.IncidencesInterface;
import com.tfc.apitfc.domain.entity.Incidence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenceService {
    @Autowired
    IncidencesInterface incidencesInterface;

    public List<Incidence> findAllIncidences() {
        return incidencesInterface.findAll();
    }

    public List<Incidence> findIncidencesByNeighborhoodId(int neighborhoodId) {
        return incidencesInterface.findIncidenceByNeighborhoodId(neighborhoodId);
    }

    public Incidence save(Incidence incidence) {
        return incidencesInterface.save(incidence);
    }
}
