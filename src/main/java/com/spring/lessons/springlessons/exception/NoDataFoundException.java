package com.spring.lessons.springlessons.exception;

public class NoDataFoundException extends  RuntimeException{
    public NoDataFoundException() {

        super("No data found");
    }
}
