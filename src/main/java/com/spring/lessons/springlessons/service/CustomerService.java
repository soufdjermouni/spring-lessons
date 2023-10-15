package com.spring.lessons.springlessons.service;

import com.spring.lessons.springlessons.domain.batch.Customer;
import com.spring.lessons.springlessons.dto.CustomerDto;
import com.spring.lessons.springlessons.dto.PersonDto;
import com.spring.lessons.springlessons.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public List<CustomerDto> getAllCustomers() {
        Page<Customer> page = customerRepository.findAll(Pageable.ofSize(1000));
        List<Customer> list = page.getContent();
        return list
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }
}
