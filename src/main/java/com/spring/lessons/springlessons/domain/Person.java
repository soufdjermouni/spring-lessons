package com.spring.lessons.springlessons.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Setter
@Getter
@Builder
@AllArgsConstructor
@NamedQuery(
        name = "Person.findByEmail",
        query = "SELECT p FROM Person p WHERE p.email = ?1")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private String email;

    private Person(){
    }
}