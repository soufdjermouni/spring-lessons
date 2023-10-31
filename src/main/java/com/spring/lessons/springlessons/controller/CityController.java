package com.spring.lessons.springlessons.controller;

import com.spring.lessons.springlessons.domain.City;
import com.spring.lessons.springlessons.exception.CityNotFoundException;
import com.spring.lessons.springlessons.exception.NoDataFoundException;
import com.spring.lessons.springlessons.repository.CityRepository;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/public")
public class CityController {

    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping(value = "/cities/{id}")
    public City getCity(@PathVariable Long id) {
        System.out.println("je suis ma ");
        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
    }

    //http://localhost:8081/api/public/cities/param?name=alger
    @GetMapping(value = "/cities/param")
    public List<City> getCityByName(@RequestParam String name) {
        return cityRepository.findByName(name);
    }

    @PostMapping(value = "/cities", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public City createCity(@RequestBody @Validated City city) {

        return cityRepository.save(city);
    }

    @GetMapping(value = "/cities")
    public Iterable<City> findAll() {
        List<City> cities = (List<City>) cityRepository.findAll();
        if (cities.isEmpty()) {
            throw new NoDataFoundException();
        }
        return cities;
    }

}
