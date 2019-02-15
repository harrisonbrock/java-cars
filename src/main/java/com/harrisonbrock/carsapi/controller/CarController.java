package com.harrisonbrock.carsapi.controller;

import com.harrisonbrock.carsapi.domain.Car;
import com.harrisonbrock.carsapi.services.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.findAll();
    }

    @PutMapping("/cars/upload")
    public List<Car> upLoadData(@RequestBody List<Car> cars) {
        return carService.upLoadData(cars);
    }
}
