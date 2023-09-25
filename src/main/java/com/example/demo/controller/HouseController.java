package com.example.demo.controller;

import com.example.demo.model.Bed;
import com.example.demo.model.House;
import com.example.demo.repository.BedRepository;
import com.example.demo.repository.HouseRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/house", produces="application/json")
@CrossOrigin(origins="*")
public class HouseController {

    private final HouseRepository repository;
    private final BedRepository bedRepository;
    public HouseController(HouseRepository repository, BedRepository bedRepository) {
        this.repository = repository;
        this.bedRepository = bedRepository;
    }

    @PostMapping({"","/"})
    public void post(@RequestParam("name") String name, @RequestParam("bed") Integer bed) {
        House house = new House(name, bed);
        for (int i = 0; i < bed; i++) {
            Bed newBed = new Bed(house);
            bedRepository.save(newBed);
        }

        repository.save(house);
    }
}
