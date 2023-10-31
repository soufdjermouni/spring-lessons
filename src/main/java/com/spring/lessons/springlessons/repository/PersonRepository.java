package com.spring.lessons.springlessons.repository;

import com.spring.lessons.springlessons.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  PersonRepository extends JpaRepository<Person, Long> {

    // JPQL query
    @Query("select p from Person p where p.name = :name")
    Person findByName(@Param("name") String name);

    //SQL query directly
    @Query(
            value = "SELECT * FROM USER AS u WHERE u.name = :name",
            nativeQuery = true)
    Person findByNameNative (@Param("name") String name);

    public Person findByEmail(String email);

}
