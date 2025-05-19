package com.tfc.apitfc.domain.dto;

import java.time.LocalDateTime;

public class ReceiptDTO {
    private String title;
    private String description;
    private double value;
    private boolean paid;
    private LocalDateTime date;
    private int neighborId;


    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }

    public boolean isPaid() { return paid; }

    public void setPaid(boolean paid) { this.paid = paid; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public int getNeighborId() { return neighborId; }

    public void setNeighborId(int neighborId) { this.neighborId = neighborId; }
}
