package com.spring.lessons.springlessons.repository;

import com.spring.lessons.springlessons.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
