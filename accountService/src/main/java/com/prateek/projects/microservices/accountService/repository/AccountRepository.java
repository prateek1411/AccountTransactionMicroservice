package com.prateek.projects.microservices.accountService.repository;

import com.prateek.projects.microservices.accountService.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
