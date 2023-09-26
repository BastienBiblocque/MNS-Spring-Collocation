package com.example.demo.service;

import com.example.demo.model.Bed;
import com.example.demo.model.House;
import com.example.demo.repository.BedRepository;
import com.example.demo.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BedServiceTest {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private BedService bedService;
    @Autowired
    private BedRepository bedRepository;

    void testGet() {
        //given
        House house = new House("maison", 1);
        houseRepository.save(house);
        Bed bed = new Bed(house);
        bedRepository.save(bed);
        //when
        Bed b = bedService.findById(bed.getId());
        //then
        assertNotNull(bed);
        assertEquals(bed.getId(),b.getId());
    }
}
