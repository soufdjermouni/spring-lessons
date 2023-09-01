package com.spring.lessons.springlessons.controller;

import com.spring.lessons.springlessons.common.annotation.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class DemoController {

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
}
