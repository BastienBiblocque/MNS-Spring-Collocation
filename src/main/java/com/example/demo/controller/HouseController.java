package com.example.demo.controller;

import com.example.demo.DTO.BookBedDTO;
import com.example.demo.DTO.CreateHouseDTO;
import com.example.demo.model.Bed;
import com.example.demo.model.House;
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
        List<House> houses = houseService.getAll();

        for (House house : houses) {
            int bedAvailable = 0;
            Set<Bed> beds = house.getBeds();
            for (Bed bed : beds) {
                if (bed.getUserId() == null) {
                    bedAvailable++;
                }
            }
            house.setBedAvailable(bedAvailable);
        }

        return ResponseEntity.ok(houseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getById(@PathVariable("id") Long id){
        House house = houseService.getById(id);

        Set<Bed> beds = house.getBeds();
        int bedAvailable = 0;
        for (Bed bed : beds) {
            if (bed.getUserId() == null) {
                bedAvailable++;
            }
        }
        house.setBedAvailable(bedAvailable);

        return ResponseEntity.ok(houseService.getById(id));
    }

    @PostMapping("/add")
    public House post(@RequestBody CreateHouseDTO request) {
        House house = new House(request.name, request.bed, request.description);
        houseService.save(house);

        for (int i = 0; i < request.bed; i++) {
            Bed newBed = new Bed(house);
            bedService.save(newBed);
        }

        return houseService.findById(house.getId());
    }

    @PostMapping("/{id}/book")
    public void book(@PathVariable(value="id") Long id, @RequestBody BookBedDTO request) {
        //check availability
        House house = houseService.findById(id);
        Set<Bed> beds = house.getBeds();

        List<Bed> bedsAvailable = new ArrayList<>();
        for (Bed bed : beds) {
            if (bed.getUserId() == null) {
                bedsAvailable.add(bed);
            }
        }

        if (bedsAvailable.size() < request.quantity) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not enough bed available"
            );
        }

        for (int i = 0; i < request.quantity; i++) {
            bedsAvailable.get(i).setUserId(request.userId);
            bedService.save(bedsAvailable.get(i));
        }
    }
}
