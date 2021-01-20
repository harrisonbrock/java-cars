package com.harrisonbrock.carsapi.services;

import com.harrisonbrock.carsapi.domain.Car;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car findById(Long id);
    List<Car> findByYear(int year);
    List<Car> findByBrand(String brand);
    List<Car> upLoadData(List<Car> cars);
    void deleteById(Long in);
}
