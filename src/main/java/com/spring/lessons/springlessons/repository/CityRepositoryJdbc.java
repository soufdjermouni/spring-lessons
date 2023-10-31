package com.spring.lessons.springlessons.repository;

import com.spring.lessons.springlessons.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepositoryJdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static  String INSERT_QUERRY =
            //TEXT BLOC
            """
                insert into cities (name, population)
                values ('alger', 44554451);
            """;

    private static  String SELECT_QUERRY =
            //TEXT BLOC
            """
                select * from cities
                where id = ?
            """;

    private static  String INSERT_QUERRY_PARAMETERS =
            //TEXT BLOC
            """
                insert into cities (name, population)
                values (?, ?);
            """;
   public void insert(City city) {
        jdbcTemplate.update(INSERT_QUERRY);
       jdbcTemplate.update(INSERT_QUERRY_PARAMETERS, city.getName(),city.getPopulation());
    }

    public City findById(Integer id) {
        return jdbcTemplate.queryForObject(SELECT_QUERRY, new BeanPropertyRowMapper<>(City.class), id);
    }
}
