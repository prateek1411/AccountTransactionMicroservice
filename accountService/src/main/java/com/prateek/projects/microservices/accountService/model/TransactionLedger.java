package com.prateek.projects.microservices.accountService.model;

import org.springframework.stereotype.Component;

@Component
public class TransactionLedger {

    private Long id;
    private Long account;
    private String transactionType;
    private Long amount;

    public TransactionLedger() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }


}
