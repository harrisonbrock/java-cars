package com.harrisonbrock.carsapi.controller;

import com.harrisonbrock.carsapi.domain.Car;
import com.harrisonbrock.carsapi.services.CarService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;
    private final RabbitTemplate rabbitTemplate;

    public CarController(CarService carService, RabbitTemplate rabbitTemplate) {
        this.carService = carService;
        this.rabbitTemplate = rabbitTemplate;
    }


    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.findAll();
    }

    @PostMapping("/cars/upload")
    public List<Car> upLoadData(@RequestBody List<Car> cars) {
        return carService.upLoadData(cars, rabbitTemplate);
    }
}
