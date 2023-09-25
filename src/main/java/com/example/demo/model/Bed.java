package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Entity
@Table(name="bed")
public class Bed implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "house_id")
    @JsonIgnore
    private House house;


    @Column
    private String userId;

    public Bed(House house) {
        this.house = house;
    }

    public Bed() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}