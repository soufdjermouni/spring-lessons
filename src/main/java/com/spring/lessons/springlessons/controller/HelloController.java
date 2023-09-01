package com.spring.lessons.springlessons.controller;

import com.spring.lessons.springlessons.feature.scope.request.RequestScopeBean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloController {

    private final RequestScopeBean requestScopeBean;

    public HelloController(RequestScopeBean requestScopeBean) {
        this.requestScopeBean = requestScopeBean;
    }


    @GetMapping
    public String getAll() {
        System.out.println("RequestScopeBean: "+requestScopeBean);
        return "Hello";
    }
}