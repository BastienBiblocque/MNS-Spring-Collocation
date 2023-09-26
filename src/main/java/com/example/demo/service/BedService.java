package com.example.demo.service;

import com.example.demo.model.Bed;
import com.example.demo.model.House;
import com.example.demo.repository.BedRepository;
import com.example.demo.repository.HouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {

    private final BedRepository bedRepository;

    public BedService(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    public Bed save(Bed bed) {
        bedRepository.save(bed);
        return bed;
    }
}
