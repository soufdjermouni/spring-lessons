package com.spring.lessons.springlessons.service;

import com.spring.lessons.springlessons.domain.Person;
import com.spring.lessons.springlessons.domain.batch.Customer;
import com.spring.lessons.springlessons.dto.CustomerDto;
import com.spring.lessons.springlessons.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private  CustomerService customerService;

    @Autowired
    private  ModelMapper modelMapper;

    @Test
    void getAllCustomers() {

        //Given
        Page<Customer> page = mock(Page.class);
        List<Customer> customers = List.of(new Customer());
        when(page.getContent()).thenReturn(customers);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(page);

        //When
        List<CustomerDto> expected = customerService.getAllCustomers();

        // Then
        List<CustomerDto> customersDto = customers
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
        assertThat(expected).isEqualTo(customersDto);
        ArgumentCaptor<Pageable> pageableArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(customerRepository).findAll(pageableArgumentCaptor.capture());
        assertThat(pageableArgumentCaptor.getValue()).isEqualTo(Pageable.ofSize(1));

    }
}