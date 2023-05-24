package com.spring.lessons.springlessons.controller;

import com.spring.lessons.springlessons.common.annotation.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //Get localhost:8080/hello?name=soufiane
    @Log
    @GetMapping("/hello")
    ResponseEntity<String> ping(@RequestParam String name){
        return ResponseEntity.ok(String.format("Hello %S!", name));
    }
}
