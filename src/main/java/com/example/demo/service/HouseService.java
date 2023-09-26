package com.example.demo.service;

import com.example.demo.model.House;
import com.example.demo.repository.HouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public List<House> getAll(){
        return houseRepository.findAll();
    }

    public House getById(long id){
        return houseRepository.findById(id).orElse(null);
    }

    public House save(House house) {
        houseRepository.save(house);
        return house;
    }
}
