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
public class HouseServiceTest {
    @Autowired
    private HouseService houseService;
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
        //when
        House house1 = houseService.findById(house.getId());
        //then
        assertNotNull(house);
        assertEquals(house.getId(),house1.getId());
    }
}
