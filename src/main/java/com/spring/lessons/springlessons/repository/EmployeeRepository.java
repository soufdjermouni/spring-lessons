package com.spring.lessons.springlessons.repository;

import com.spring.lessons.springlessons.domain.clecomposite.Employee;
import com.spring.lessons.springlessons.domain.clecomposite.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId> {
}
