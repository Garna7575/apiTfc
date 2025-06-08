package com.tfc.apitfc.domain.dto;

import com.tfc.apitfc.domain.entity.Receipt;

import java.util.List;

public class PaymentEmailDTO {
    String email;
    String name;
    List<Receipt> receipts;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}
