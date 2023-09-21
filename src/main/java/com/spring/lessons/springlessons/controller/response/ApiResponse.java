package com.spring.lessons.springlessons.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse {

    private Boolean deleted;
    private String message;
    private HttpStatus status;
}
