package com.spring.lessons.springlessons.config;

import com.spring.lessons.springlessons.domain.Person;
import com.spring.lessons.springlessons.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Person(1L, "Soufiane", "Mr")));
            log.info("Preloading " + repository.save(new Person(2L, "Halima", "Mme")));
        };
    }
}
