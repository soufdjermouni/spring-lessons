package com.spring.lessons.springlessons.controller;

import com.spring.lessons.springlessons.controller.utils.response.ApiResponse;
import com.spring.lessons.springlessons.domain.Person;
import com.spring.lessons.springlessons.dto.PersonDto;
import com.spring.lessons.springlessons.exception.PersonNotFoundException;
import com.spring.lessons.springlessons.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/public")
public class PersonController {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonController(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/persons")
    public Iterable<PersonDto> findAllPersons() {
        return this.personRepository.findAll().stream().map(person -> modelMapper.map(person, PersonDto.class))
                .collect(Collectors.toList());
    }

    // Sans DynamicUpdate : Hibernate: update person set email=?, genre=?, name=? where id=?
    @GetMapping("/person/testdynamicupdate")
    public @ResponseBody String testdynamicupdate() {

        Optional<Person> account = personRepository.findById(2L);
        if(account.isPresent()){
            account.get().setName("Hlimaaaaaaaaaaaaa");
            personRepository.save(account.get());
        }

        return "Ok";
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
    EntityModel<PersonDto> one(@PathVariable Long id) throws PersonNotFoundException {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        PersonDto personResponse = modelMapper.map(person, PersonDto.class);

        //hateoas  : ajout des liens
        return EntityModel.of(personResponse, //
                linkTo(methodOn(PersonController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PersonController.class).findAllPersons()).withRel("persons"));
    }


    @PostMapping("/persons")
    public PersonDto addOneEmployee(@RequestBody PersonDto employeeDto) {
        // convert DTO to entity
        Person personRequest = modelMapper.map(employeeDto, Person.class);
        Person person = this.personRepository.save(personRequest);
        // convert entity to DTO
        return  modelMapper.map(person, PersonDto.class);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<PersonDto> updatePost(@PathVariable long id, @RequestBody PersonDto postDto) {

        // convert DTO to Entity
        Person postRequest = modelMapper.map(postDto, Person.class);
        postRequest.setId(id);
        Person person = personRepository.save(postRequest);

        // entity to DTO
        PersonDto personResponse = modelMapper.map(person, PersonDto.class);

        return ResponseEntity.ok().body(personResponse);
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<ApiResponse> deletePerson(@PathVariable(name = "id") Long id) {
        personRepository.deleteById(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Post deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
