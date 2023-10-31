package com.spring.lessons.springlessons.repository;

import com.spring.lessons.springlessons.domain.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.liquibase.enabled=false",
        "spring.flyway.enabled=false"
})
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    @Test
    void findByEmail() {
        Person person  = getPerson();
        personRepository.save(person);
        Person result = personRepository.findByEmail(person.getEmail());
        assertNotNull(result);
    }

    private Person getPerson() {
        return Person
                .builder()
                .name("Toto Titi")
                .genre("M")
                .email("toto.titi@gmail.com")
                .build();
    }

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(personRepository).isNotNull();
    }

    @Test
    @Sql("createPerson.sql")
    void whenInitializedByDbUnit_thenFindsByName() {
        Person user = personRepository.findByName("Zaphod Beeblebrox");
        assertThat(user).isNotNull();
    }

    @Test
    void findByName() {
    }

    @Test
    void findByNameNative() {
    }

    private Optional<Person> person() {
        return Optional.of(Person.builder()
                .id(42L)
                .name("Hurley")
                .build());
    }

}