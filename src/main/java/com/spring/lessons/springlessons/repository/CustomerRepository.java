package com.spring.lessons.springlessons.repository;

import com.spring.lessons.springlessons.domain.batch.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
