package com.example.demo.repository;

import com.example.demo.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long> {

}
