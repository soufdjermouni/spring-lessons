package com.spring.lessons.springlessons.controller.Compositekey;

import com.spring.lessons.springlessons.domain.clecomposite.Employee;
import com.spring.lessons.springlessons.domain.clecomposite.EmployeeId;
import com.spring.lessons.springlessons.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{employeeId}/{companyId}")
    public Employee getEmployeeById(@PathVariable Long employeeId, @PathVariable Long companyId) {
        EmployeeId id = new EmployeeId(employeeId, companyId);
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping("employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
}
