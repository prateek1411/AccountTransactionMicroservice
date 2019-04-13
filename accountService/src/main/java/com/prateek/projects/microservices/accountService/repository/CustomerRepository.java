package com.prateek.projects.microservices.accountService.repository;

import com.prateek.projects.microservices.accountService.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
