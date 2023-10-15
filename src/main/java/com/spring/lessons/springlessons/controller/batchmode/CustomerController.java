package com.spring.lessons.springlessons.controller.batchmode;

import com.spring.lessons.springlessons.domain.batch.Customer;
import com.spring.lessons.springlessons.domain.batch.Request;
import com.spring.lessons.springlessons.dto.CustomerDto;
import com.spring.lessons.springlessons.repository.CustomerRepository;
import com.spring.lessons.springlessons.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final EntityManager entityManager;

    @PostMapping("/customers")
    public ResponseEntity<String> insertCustomers() {
        Customer c1 = new Customer("James", "Gosling");
        Customer c2 = new Customer("Doug", "Lea");
        Customer c3 = new Customer("Martin", "Fowler");
        Customer c4 = new Customer("Brian", "Goetz");
        List<Customer> customers = Arrays.asList(c1, c2, c3, c4);
        customerRepository.saveAll(customers);

        return ResponseEntity.ok("Created");
    }

    @GetMapping("/customers")
    public List<CustomerDto> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/createbatch")
    @Transactional
    public ResponseEntity<String> createbatch(){

        for (int i = 0; i < 5; i++) {
            Customer customer = new Customer("firstname-" + i, "lastname-" + i );
            for (int j = 0; j < 10; j++) {
                customer.addRequest(new Request("Code-" + i + "-" + j));
            }
            entityManager.persist(customer);
        }

        return ResponseEntity.ok("Bien déroulé");
    }

    @GetMapping("/testbatch")
    @Transactional
    public ResponseEntity<String> testbatch(){

        List<Customer> customers = entityManager.createQuery("from Customer", Customer.class).getResultList();
        for (Customer customer : customers) {
            customer.getRequests().size();
        }
        return ResponseEntity.ok("Bien déroulé");
    }
}
