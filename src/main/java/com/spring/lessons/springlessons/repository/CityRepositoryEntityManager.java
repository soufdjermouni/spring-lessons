package com.spring.lessons.springlessons.repository;

import com.spring.lessons.springlessons.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CityRepositoryEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    public void insert(City city) {
        entityManager.merge(city);
    }

    public City findById(Integer id) {
        return entityManager.find(City.class, id);
    }

    public void deleteById(Integer id) {
        City city = entityManager.find(City.class, id);
        entityManager.remove(city);
    }
}
