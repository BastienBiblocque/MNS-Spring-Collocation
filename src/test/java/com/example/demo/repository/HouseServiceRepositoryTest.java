package com.example.demo.repository;

import com.example.demo.model.House;
import com.example.demo.service.BedService;
import com.example.demo.service.HouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class HouseServiceRepositoryTest {

    @Autowired
    private HouseService houseService;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private BedService bedService;
    @Autowired
    private BedRepository bedRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testGet() {
        //given
        House house = new House("maison", 1);
        entityManager.persist(house);
        Optional<House> optionalHouse = houseRepository.findById(4L);
        //when
        //then
        assertTrue(optionalHouse.isPresent());
        assertEquals("maison",optionalHouse.get().getName());
    }
}
