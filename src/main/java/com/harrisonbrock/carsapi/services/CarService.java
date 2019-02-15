package com.harrisonbrock.carsapi.services;

import com.harrisonbrock.carsapi.domain.Car;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();
    Optional<Car> findById(Long id);
    List<Car> findByYear(int year);
    List<Car> findByBrand(String brand, RabbitTemplate rabbitTemplate);
    List<Car> upLoadData(List<Car> cars, RabbitTemplate rabbitTemplate);
}
