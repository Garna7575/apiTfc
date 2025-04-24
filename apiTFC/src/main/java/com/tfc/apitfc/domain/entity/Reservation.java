package com.tfc.apitfc.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "common_area_id", nullable = false)
    @JsonBackReference(value = "commonarea-reservations")
    private CommonArea commonArea;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "neighbor_id", nullable = false)
    @JsonBackReference(value = "neighbor-reservations")
    private Neighbor neighbor;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommonArea getCommonArea() {
        return commonArea;
    }

    public void setCommonArea(CommonArea commonArea) {
        this.commonArea = commonArea;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Neighbor getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(Neighbor neighbor) {
        this.neighbor = neighbor;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", commonArea=" + commonArea +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", neighbor=" + neighbor +
                '}';
    }
}