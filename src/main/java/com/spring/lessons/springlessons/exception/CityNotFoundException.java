package com.spring.lessons.springlessons.exception;

public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException(Long id) {
        super(String.format("City with Id %d not found", id));
    }
}
