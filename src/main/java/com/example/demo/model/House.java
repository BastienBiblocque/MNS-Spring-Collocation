package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name="house")
public class House implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer bed;

    private Integer bedAvailable;

    @OneToMany(mappedBy = "house")
    private Set<Bed> beds;

    public House(String name, Integer bed, String description) {
        this.name = name;
        this.bed = bed;
        this.id = 0L;
        this.description = description;

    }

    public House() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBed() {
        return bed;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    public Set<Bed> getBeds() {
        return beds;
    }

    public void setBeds(Set<Bed> beds) {
        this.beds = beds;

    public Integer getBedAvailable() {
        return bedAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBedAvailable(Integer bedAvailable) {
        this.bedAvailable = bedAvailable;
    }
}