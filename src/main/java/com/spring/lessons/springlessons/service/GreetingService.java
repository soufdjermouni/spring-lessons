package com.spring.lessons.springlessons.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Scope("singleton")
public class GreetingService {

    public String greet() {
        return "Hello, Mock";
    }
}
