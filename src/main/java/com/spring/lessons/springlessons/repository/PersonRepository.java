package com.spring.lessons.springlessons.repository;

import com.spring.lessons.springlessons.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  PersonRepository extends JpaRepository<Person, Long> {


}
