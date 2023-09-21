package com.spring.lessons.springlessons.controller;

import com.spring.lessons.springlessons.domain.Person;
import com.spring.lessons.springlessons.exception.PersonNotFoundException;
import com.spring.lessons.springlessons.repository.PersonRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public Iterable<Person> findAllPersons() {
        return this.personRepository.findAll();
    }

    /**
     * HATEOAS
     * Le principe est qu'un client interagit avec une application réseau entièrement par hypermédia fournie
     * dynamiquement par les serveurs d'applications.
     *
     * Exemple avec HATEOAS
     *  {{
     *     "id": 1,
     *     "name": "Soufiane",
     *     "_links": {
     *         "self": {
     *             "href": "http://localhost:8080/api/persons/1"
     *         },
     *         "persons": {
     *             "href": "http://localhost:8080/api/persons"
     *         }
     *     }
     * }
     *
     * @param id
     * @return
     * @throws PersonNotFoundException
     */
    @GetMapping("/persons/{id}")
    EntityModel<Person> one(@PathVariable Long id) throws PersonNotFoundException {

        Person employee = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        //hateoas  : ajout des liens
        return EntityModel.of(employee, //
                linkTo(methodOn(PersonController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PersonController.class).findAllPersons()).withRel("persons"));
    }
}
