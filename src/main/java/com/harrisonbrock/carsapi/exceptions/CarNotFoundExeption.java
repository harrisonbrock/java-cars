package com.harrisonbrock.carsapi.exceptions;

public class CarNotFoundExeption extends RuntimeException {

    public CarNotFoundExeption(Long id)  {
        super("Could not find Car with id: " + id);
    }
}
