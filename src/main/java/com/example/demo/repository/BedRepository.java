package com.example.demo.repository;

import com.example.demo.model.Bed;
import com.example.demo.model.House;
import org.springframework.data.repository.CrudRepository;

public interface BedRepository extends CrudRepository<Bed, Long> {
}
