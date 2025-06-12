package com.tfc.apitfc.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("vote-neighbors")
    private List<NeighborVote> neighborVotes;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    @JsonBackReference("neighborhood-votes")
    private Neighborhood neighborhood;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public List<NeighborVote> getNeighborVotes() {
        return neighborVotes;
    }

    public void setNeighborVotes(List<NeighborVote> neighborVotes) {
        this.neighborVotes = neighborVotes;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", neighborVotes=" + neighborVotes +
                ", neighborhood=" + neighborhood +
                '}';
    }
}
