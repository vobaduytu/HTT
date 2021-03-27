package com.tu.service.impl;

import com.tu.model.Customer;
import com.tu.repository.CustomerRepository;
import com.tu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Page<Customer> showAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void Save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(long id) {
        customerRepository.deleteById(id);
    }
}
