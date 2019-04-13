package com.prateek.projects.microservices.accountService.api;

import com.prateek.projects.microservices.accountService.Constants;
import com.prateek.projects.microservices.accountService.model.Customer;
import com.prateek.projects.microservices.accountService.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerApi {

    private CustomerRepository repository;

    CustomerApi(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    @GetMapping(path = {Constants.CUSTOMER_GET_ALL})
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {Constants.CUSTOMER_GET})
    public ResponseEntity<Customer> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = {Constants.CUSTOMER_ADD})
    public Customer create(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @PutMapping(path = {Constants.CUSTOMER_UPDATE})
    public ResponseEntity<Customer> update(@PathVariable("id") long id,
                                           @RequestBody Customer customer) {
        return repository.findById(id)
                .map(record -> {
                    record.setFirstName(customer.getFirstName());
                    record.setLastName(customer.getLastName());
                    Customer updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {Constants.CUSTOMER_DELETE})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    // CRUD methods here
}

