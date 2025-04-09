package com.tfc.apitfc.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "neighborhood")
public class Neighborhood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Lob
    @JdbcTypeCode(SqlTypes.BINARY)
    private byte[] image;

    @OneToMany(mappedBy = "neighborhood")
    @JsonManagedReference(value = "neighborhood-neighbors")
    private List<Neighbor> neighbors;

    @OneToMany(mappedBy = "neighborhood")
    @JsonManagedReference(value = "neighborhood-commonareas")
    private List<CommonArea> commonAreas;

    @Transient
    private String base64Image;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonBackReference(value = "admin-neighborhoods")
    private Admin admin;

    @OneToMany(mappedBy = "neighborhood")
    @JsonManagedReference("neighborhood-votes")
    private List<Vote> votes;

    @OneToMany(mappedBy = "neighborhood")
    @JsonManagedReference("neighborhood-records")
    private List<Record> records;

    @OneToMany(mappedBy = "neighborhood")
    @JsonManagedReference(value = "neighborhood-incidences")
    private List<Incidence> incidences;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Neighbor> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Neighbor> neighbors) {
        this.neighbors = neighbors;
    }

    public List<CommonArea> getCommonAreas() {
        return commonAreas;
    }

    public void setCommonAreas(List<CommonArea> commonAreas) {
        this.commonAreas = commonAreas;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Incidence> getIncidences() {
        return incidences;
    }

    public void setIncidences(List<Incidence> incidences) {
        this.incidences = incidences;
    }
}
