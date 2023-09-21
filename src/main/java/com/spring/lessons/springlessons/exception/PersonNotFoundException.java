package com.spring.lessons.springlessons.exception;

public class PersonNotFoundException extends  Exception{
    public PersonNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
