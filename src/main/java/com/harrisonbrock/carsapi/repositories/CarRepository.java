package com.harrisonbrock.carsapi.repositories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harrisonbrock.carsapi.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByYear(int year);
    List<Car> findByBrandIgnoreCase(String brand);
}
