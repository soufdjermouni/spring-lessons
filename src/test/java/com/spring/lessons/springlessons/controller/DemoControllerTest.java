package com.spring.lessons.springlessons.controller;

import com.spring.lessons.springlessons.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoControllerTest {

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private DemoController demoController;

    @Test
    public void contextLoads(){
        Assertions.assertNotNull(demoController);
        Assertions.assertNotNull(personRepository);
    }
}