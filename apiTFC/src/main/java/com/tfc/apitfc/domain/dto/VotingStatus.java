package com.tfc.apitfc.domain.dto;

public class VotingStatus {
    private int inFavor;
    private int against;
    private int total;

    public int getInFavor() {
        return inFavor;
    }

    public void setInFavor(int inFavor) {
        this.inFavor = inFavor;
    }

    public int getAgainst() {
        return against;
    }

    public void setAgainst(int against) {
        this.against = against;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void addInFavor() {
        inFavor++;
    }

    public void addAgainst() {
        against++;
    }
}
