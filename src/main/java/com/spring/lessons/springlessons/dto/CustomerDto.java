package com.spring.lessons.springlessons.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class CustomerDto {

    private Long id;

    private String firstName;

    private String lastName;
}
