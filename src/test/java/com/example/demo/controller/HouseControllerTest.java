package com.example.demo.controller;

import com.example.demo.controller.HouseController;
import com.example.demo.DTO.BookBedDTO;
import com.example.demo.DTO.CreateHouseDTO;
import com.example.demo.model.Bed;
import com.example.demo.model.House;
import com.example.demo.repository.BedRepository;
import com.example.demo.repository.HouseRepository;
import com.example.demo.service.BedService;
import com.example.demo.service.HouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = "spring.config.name=application-test")
public class HouseControllerTest {

    @InjectMocks
    private HouseController houseController;

    @Mock
    private HouseService houseService;

    @Mock
    private BedService bedService;

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private BedRepository bedRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        houseController = new HouseController(houseService, bedService);
    }

    @Test
    public void testGetAll() {
        List<House> houses = new ArrayList<>();
        houses.add(new House("House1", 5));
        houses.add(new House("House2", 3));

        when(houseService.findAll()).thenReturn(houses);

        ResponseEntity<List<House>> response = houseController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(houses, response.getBody());
    }

    @Test
    public void testGetById() {
        Long houseId = 1L;
        House house = new House("House1", 5);
        house.setId(houseId);

        when(houseService.findById(houseId)).thenReturn(house);

        ResponseEntity<House> response = houseController.getById(houseId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(house, response.getBody());
    }

//    @Test
//    public void testPost() {
//        // Create and save a House entity with a specific ID for the test
//        House house = new House("House1", 5);
//        house.setId(1L);
//        houseRepository.save(house);
//
//        // Créez un objet CreateHouseDTO pour simuler la demande
//        CreateHouseDTO createHouseDTO = new CreateHouseDTO();
//        createHouseDTO.setName("House1");
//        createHouseDTO.setBed(3);
//
//        // Créez un objet House attendu en fonction de la demande
//        House expectedHouse = new House(createHouseDTO.getName(), createHouseDTO.getBed());
//
//        // Mock the houseService.save method to return a non-null House object with an ID
//        when(houseService.save(any(House.class))).thenAnswer(invocation -> {
//            House savedHouse = invocation.getArgument(0);
//            savedHouse.setId(1L); // Simulate saving a house and assigning an ID
//            return savedHouse;
//        });
//
//        // Mock the bedService.save method to return a non-null Bed object with an ID
//        when(bedService.save(any(Bed.class))).thenAnswer(invocation -> {
//            Bed savedBed = invocation.getArgument(0);
//            savedBed.setId(1L); // Simulate saving a bed and assigning an ID
//            return savedBed;
//        });
//
//        // Appelez la méthode post du contrôleur
//        Long houseId = 1L;
//        System.out.println(houseRepository.findById(houseId));
//        House response = houseController.post(createHouseDTO);
//
//        System.out.println(response);
//
//        // Vérifiez que la méthode houseService.save a été appelée une fois
//        verify(houseService, times(1)).save(any(House.class));
//
//        // Vérifiez que la réponse n'est pas nulle
//        assertNotNull(response);
//
//        // Vérifiez que les champs importants de la réponse correspondent aux champs de l'objet attendu
//        assertEquals(expectedHouse.getName(), response.getName());
//        assertEquals(expectedHouse.getBed(), response.getBed());
//    }

    @Test
    public void testBook() {
        Long houseId = 1L;

        // Create a house with 5 available beds
        House house = new House("House1", 5);
        house.setId(houseId);

        // Initialize the 'beds' property with available beds
        Set<Bed> beds = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            beds.add(new Bed());
        }
        house.setBeds(beds);

        // Mock the houseService.findById to return the house with available beds
        when(houseService.findById(houseId)).thenReturn(house);

        // Mock the bedService.save method to return a non-null Bed object with an ID
        when(bedService.save(any(Bed.class))).thenAnswer(invocation -> {
            Bed bed = invocation.getArgument(0);
            bed.setId(1L); // Simulate saving a bed and assigning an ID
            return bed;
        });

        // Create a booking request for 3 beds
        BookBedDTO bookBedDTO = new BookBedDTO("user123", 3);

        // Call the book method on the controller
        houseController.book(houseId, bookBedDTO);

        // Check that the number of beds booked matches the requested number
        assertEquals(3, house.getBeds().stream().filter(bed -> bed.getUserId() != null).count());

        // Check that the number of available beds has decreased accordingly
        assertEquals(2, house.getBeds().stream().filter(bed -> bed.getUserId() == null).count());

        // Verify that the bedService.save method was called 3 times (for each booked bed)
        verify(bedService, times(3)).save(any(Bed.class));
    }

    @Test
    public void testBookNotEnoughBedsAvailable() {
        Long houseId = 1L;
        House house = new House("House1", 5);
        house.setId(houseId);

        // Initialize the 'beds' property with some beds
        Set<Bed> beds = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            Bed bed = new Bed(house);
            bed.setUserId("user123");
            beds.add(bed);
        }
        house.setBeds(beds); // Set the 'beds' property of the house

        when(houseService.findById(houseId)).thenReturn(house);

        BookBedDTO bookBedDTO = new BookBedDTO("user456", 3);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> houseController.book(houseId, bookBedDTO));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Not enough bed available", exception.getReason());
    }

}
