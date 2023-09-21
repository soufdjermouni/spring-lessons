package com.spring.lessons.springlessons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PersonNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler  //configures the advice to only respond if an PersonNotFoundException is thrown.
    @ResponseStatus(HttpStatus.NOT_FOUND)  //HTTP 404
    String handleEmployeeNotFound(PersonNotFoundException ex) {
        return ex.getMessage();
    }
}
