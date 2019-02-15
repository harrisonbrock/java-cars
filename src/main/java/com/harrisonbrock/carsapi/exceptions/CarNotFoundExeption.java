package com.harrisonbrock.carsapi.exceptions;

import java.util.function.Supplier;

public class CarNotFoundExeption extends RuntimeException {

    public CarNotFoundExeption(Long id)  {
        super("Could not find Car with id: " + id);
    }

}
