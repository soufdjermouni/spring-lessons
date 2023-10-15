package com.spring.lessons.springlessons.controller;

import com.spring.lessons.springlessons.common.annotation.Log;
import com.spring.lessons.springlessons.service.GreetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class DemoController {

    private final GreetingService greetingService;

    //Get localhost:8080/hello?name=soufiane
    @Log
    @GetMapping("/hello")
    @Operation(summary = "Démonstration Swagger 3 openapi : Say Hello World!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Say Hello World!",
                    content = {@Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Say Hello world not found ",
                    content = @Content)})
    ResponseEntity<String> ping(@RequestParam String name){
        return ResponseEntity.ok(String.format("Hello %S!", name));
    }

    @GetMapping("/greeting")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    @GetMapping("/greet")
    public @ResponseBody String greet() {
        return greetingService.greet();
    }
}
