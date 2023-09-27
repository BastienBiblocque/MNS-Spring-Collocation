package com.example.demo.service;

import com.example.demo.model.Bed;
import com.example.demo.model.House;
import com.example.demo.repository.BedRepository;
import com.example.demo.repository.HouseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
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

    @Test
    void testGetAll() {
        //given
        List<House> houseList = houseRepository.findAll();
        int numberOfHouse = houseList.size();
        //when
        House house = new House("maison", 1);
        houseRepository.save(house);
        houseList = houseRepository.findAll();
        //then
        assertNotNull(houseList);
        System.out.println(houseList.size());
        assertEquals((numberOfHouse+1),houseList.size());
    }
}
