package com.tfc.apitfc.domain.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class ReservationDTO {
    private int commonAreaId;

    private int neighborId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public int getCommonAreaId() {
        return commonAreaId;
    }

    public void setCommonAreaId(int commonAreaId) {
        this.commonAreaId = commonAreaId;
    }

    public int getNeighborId() {
        return neighborId;
    }

    public void setNeighborId(int neighborId) {
        this.neighborId = neighborId;
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

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "commonAreaId=" + commonAreaId +
                ", neighborId=" + neighborId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
