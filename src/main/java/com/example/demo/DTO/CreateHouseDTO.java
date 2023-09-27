package com.example.demo.DTO;

public class CreateHouseDTO {
    public String name;
    public Integer bed;
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
}
