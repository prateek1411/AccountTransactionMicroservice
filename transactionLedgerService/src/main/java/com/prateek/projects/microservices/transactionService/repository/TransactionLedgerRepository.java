package com.prateek.projects.microservices.transactionService.repository;

import com.prateek.projects.microservices.transactionService.model.TransactionLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionLedgerRepository extends JpaRepository<TransactionLedger, Long> {
    List<TransactionLedger> findByAccount(Long accountId);
}
