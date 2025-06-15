package com.tfc.apitfc.domain.dto;


public class CommonAreaDTO {
    private int id;

    private String name;

    private int neighborhoodId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeighborhoodId() {
        return neighborhoodId;
    }

    public void setNeighborhoodId(int neighborhoodId) {
        this.neighborhoodId = neighborhoodId;
    }

    @Override
    public String toString() {
        return "CommonAreaDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", neighborhoodId=" + neighborhoodId +
                '}';
    }
}
