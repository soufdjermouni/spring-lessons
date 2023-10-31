package com.spring.lessons.springlessons.exception;

public class RessourceNotFoundException extends  Exception{
    public RessourceNotFoundException(Long id) {
        super("Could not find resource " + id);
    }
}
