package com.tfc.apitfc.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

public class ReceiptDTO {
    private String title;
    private String description;
    private double value;
    private boolean paid;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date date;
    private int neighborId;


    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }

    public boolean isPaid() { return paid; }

    public void setPaid(boolean paid) { this.paid = paid; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public int getNeighborId() { return neighborId; }

    public void setNeighborId(int neighborId) { this.neighborId = neighborId; }
}
