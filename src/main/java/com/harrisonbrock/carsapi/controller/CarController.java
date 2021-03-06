package com.harrisonbrock.carsapi.controller;

import com.harrisonbrock.carsapi.domain.Car;
import com.harrisonbrock.carsapi.services.CarService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/cars/id/{id}")
    public Car getCarById(@PathVariable long id) {

        return carService.findById(id);
    }

    @GetMapping("/cars/year/{year}")
    public List<Car> getCarByYear(@PathVariable int year) {
        return carService.findByYear(year);
    }

    @GetMapping("/cars/brand/{brand}")
    public List<Car> getCarByBrand(@PathVariable String brand) {

        return carService.findByBrand(brand);
    }

    @PostMapping("/cars/upload")
    public List<Car> upLoadData(@RequestBody List<Car> cars) {

        return carService.upLoadData(cars);
    }

    @DeleteMapping("/cars/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        carService.deleteById(id);
    }
}
