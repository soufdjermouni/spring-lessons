package com.spring.lessons.springlessons;

import com.spring.lessons.springlessons.domain.City;
import com.spring.lessons.springlessons.repository.CityRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CityRepositoryImplCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CityRepositoryJdbc cityRepositoryJdbc;

    @Override
    public void run(String... args) throws Exception {
        City city = new City();
        city.setName("Setif");
        city.setPopulation(4452115);
        cityRepositoryJdbc.insert(city);
    }
}
