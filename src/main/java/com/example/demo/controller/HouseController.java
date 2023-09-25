package com.example.demo.controller;

import com.example.demo.model.Bed;
import com.example.demo.model.House;
import com.example.demo.repository.BedRepository;
import com.example.demo.repository.HouseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @GetMapping("")
    public Object get() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(value="id") String id) {
        return repository.findById(Long.valueOf(id));
    }

    @PostMapping("/add")
    public void post(@RequestParam("name") String name, @RequestParam("bed") Integer bed) {
        House house = new House(name, bed);
        repository.save(house);

        for (int i = 0; i < bed; i++) {
            Bed newBed = new Bed(house);
            bedRepository.save(newBed);
        }
    }

    @PostMapping("/{id}/book")
    public void book(@PathVariable(value="id") String id, @RequestParam("quantity") Integer quantity, @RequestParam("userId") String userId) {
        //check availability
        House house = repository.getById(Long.valueOf(id));
        Set<Bed> beds = house.getBeds();

        List<Bed> bedsAvailable = new ArrayList();
        for (Bed bed : beds) {
            System.out.println(bed);
            if (bed.getUserId() == null) {
                bedsAvailable.add(bed);
            }
        }

        if (bedsAvailable.size() < quantity) {
            // blabla error
        }

        for (int i = 0; i < quantity; i++) {
            bedsAvailable.get(i).setUserId(userId);
            bedRepository.save(bedsAvailable.get(i));
        }
    }
}
