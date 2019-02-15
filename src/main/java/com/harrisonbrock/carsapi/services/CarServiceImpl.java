package com.harrisonbrock.carsapi.services;

import com.harrisonbrock.carsapi.CarsApiApplication;
import com.harrisonbrock.carsapi.domain.Car;
import com.harrisonbrock.carsapi.logging.CarMessage;
import com.harrisonbrock.carsapi.repositories.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

    private CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Car> findById(Long id) {

        return repository.findById(id);
    }

    @Override
    public List<Car> findByYear(int year) {
        return repository.findByYear(year);
    }

    @Override
    public List<Car> findByBrand(String brand, RabbitTemplate rabbitTemplate) {
        CarMessage message = new CarMessage("User Search For Brand: " + brand);
        rabbitTemplate.convertAndSend(CarsApiApplication.QUEUE_CARS, message.toString());
        return repository.findByBrand(brand);
    }

    @Override
    public List<Car> upLoadData(List<Car> cars, RabbitTemplate rabbitTemplate) {
        CarMessage message = new CarMessage("Load Car Data");
        rabbitTemplate.convertAndSend(CarsApiApplication.QUEUE_CARS, message.toString());
        return repository.saveAll(cars);
    }
}
