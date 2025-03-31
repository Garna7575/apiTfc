package com.tfc.apitfc.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "neighbor_vote")
public class NeighborVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "neighbor_id", nullable = false)
    @JsonBackReference("neighbor-votes")
    private Neighbor neighbor;

    @ManyToOne
    @JoinColumn(name = "vote_id", nullable = false)
    @JsonBackReference("vote-neighbors")
    private Vote vote;

    private boolean inFavor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Neighbor getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(Neighbor neighbor) {
        this.neighbor = neighbor;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public boolean isInFavor() {
        return inFavor;
    }

    public void setInFavor(boolean inFavor) {
        this.inFavor = inFavor;
    }
}
