package com.harrisonbrock.carsapi.services;

import com.harrisonbrock.carsapi.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();
    Optional<Car> findById(Long id);
    List<Car> findByYear(int year);
    List<Car> findByBrand(String brand);
}
