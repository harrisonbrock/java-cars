package com.harrisonbrock.carsapi.services;

import com.harrisonbrock.carsapi.CarsApiApplication;
import com.harrisonbrock.carsapi.domain.Car;
import com.harrisonbrock.carsapi.exceptions.CarNotFoundExeption;
import com.harrisonbrock.carsapi.logging.CarMessage;
import com.harrisonbrock.carsapi.repositories.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

    private CarRepository repository;
    private RabbitTemplate  rabbitTemplate;

    public CarServiceImpl(CarRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Car findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new CarNotFoundExeption(id));
    }

    @Override
    public List<Car> findByYear(int year) {
        return repository.findByYear(year);
    }

    @Override
    public List<Car> findByBrand(String brand) {
        CarMessage message = new CarMessage("User Search For Brand: " + brand);
        rabbitTemplate.convertAndSend(CarsApiApplication.QUEUE_CARS, message.toString());
        return repository.findByBrandIgnoreCase(brand);
    }

    @Override
    public List<Car> upLoadData(List<Car> cars) {
        CarMessage message = new CarMessage("Load Car Data");
        rabbitTemplate.convertAndSend(CarsApiApplication.QUEUE_CARS, message.toString());
        return repository.saveAll(cars);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
        CarMessage message = new CarMessage(id + " Data Deleted");
        rabbitTemplate.convertAndSend(CarsApiApplication.QUEUE_CARS, message.toString());
    }
}
