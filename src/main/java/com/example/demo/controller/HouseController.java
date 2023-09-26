package com.example.demo.controller;

import com.example.demo.model.Bed;
import com.example.demo.model.House;
import com.example.demo.repository.BedRepository;
import com.example.demo.repository.HouseRepository;
import com.example.demo.service.BedService;
import com.example.demo.service.HouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="/house", produces="application/json")
@CrossOrigin(origins="*")
public class HouseController {

    private final HouseService houseService;
    private final BedService bedService;
    public HouseController(HouseService houseService, BedService bedService) {
        this.houseService = houseService;
        this.bedService = bedService;

    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<House>> getAll(){
        return ResponseEntity.ok(houseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(houseService.getById(id));
    }

    @PostMapping("/add")
    public House post(@RequestParam("name") String name, @RequestParam("bed") Integer bed) {
        House house = new House(name, bed);
        houseService.save(house);

        for (int i = 0; i < bed; i++) {
            Bed newBed = new Bed(house);
            bedService.save(newBed);
        }
        return house;
    }

    @PostMapping("/{id}/book")
    public void book(@PathVariable(value="id") Long id, @RequestParam("quantity") Integer quantity, @RequestParam("userId") String userId) {
        //check availability
        House house = houseService.getById(id);
        Set<Bed> beds = house.getBeds();

        List<Bed> bedsAvailable = new ArrayList();
        for (Bed bed : beds) {
            if (bed.getUserId() == null) {
                bedsAvailable.add(bed);
            }
        }

        if (bedsAvailable.size() < quantity) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not enough bed available"
            );
        }

        for (int i = 0; i < quantity; i++) {
            bedsAvailable.get(i).setUserId(userId);
            bedService.save(bedsAvailable.get(i));
        }
    }
}
