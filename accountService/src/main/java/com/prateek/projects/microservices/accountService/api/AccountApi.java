package com.prateek.projects.microservices.accountService.api;

import com.prateek.projects.microservices.accountService.Constants;
import com.prateek.projects.microservices.accountService.model.Account;
import com.prateek.projects.microservices.accountService.model.Customer;
import com.prateek.projects.microservices.accountService.model.TransactionLedger;
import com.prateek.projects.microservices.accountService.repository.AccountRepository;
import com.prateek.projects.microservices.accountService.repository.CustomerRepository;
import com.prateek.projects.microservices.accountService.serviceProxy.AccountApiServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountApi {

    Logger logger = LoggerFactory.getLogger(AccountApi.class);

    @Autowired
    private AccountRepository repository;
    @Autowired
    private CustomerRepository custRepository;
    @Autowired
    private AccountApiServiceProxy proxy;
    @Autowired
    private CustomerApi customerApi;

    private List<TransactionLedger> transactionLedger;

    /*
     * List all Transaction and balance for a account
     */
    @GetMapping(path = Constants.TRANSACTION_GET_ALL_ACCOUNT)
    @ResponseBody
    public ResponseEntity<Object> listAccountAndTransaction(@PathVariable("id") Long accountId) {
        HashMap<String, Object> map = new HashMap<>();
        ResponseEntity<Account> acc = findById(accountId);
        if (acc.hasBody()) {
            ResponseEntity<Customer> cust = customerApi.findById(acc.getBody().getCustomer());
            logger.debug(cust.getBody().toString());
            map.put("customer", cust.getBody());
            List<TransactionLedger> transactions = proxy.listTransactions(accountId);
            Long accountBalance = proxy.getBalance(accountId);
            if (accountBalance != null) {
                acc.getBody().setCurrentAccountBalance(accountBalance);
                repository.save(acc.getBody());
            }
            map.put("account", acc.getBody());
            map.put("transaction", transactions);
        }
        logger.info("Processed Get Transaction for account " + accountId);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @GetMapping(path = {Constants.ACCOUNTS_GET_ALL})
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {Constants.ACCOUNTS_GET})
    public ResponseEntity<Account> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    /*
     * Adding a account with initial amount
     */

    @PostMapping(path = {Constants.ACCOUNTS_ADD})
    @Transactional
    public ResponseEntity<String> create(@RequestBody Account account) {
        if (custRepository != null) {
            Optional<Customer> optionalCustomer = custRepository.findById(account.getCustomer());
            if (optionalCustomer != null && optionalCustomer.isPresent()) {
                repository.save(account);
                if (account.getCurrentAccountBalance() != null && account.getCurrentAccountBalance() != 0) {
                    logger.debug("initail amount is " + account.getCurrentAccountBalance());
                    TransactionLedger transaction = new TransactionLedger();
                    transaction.setAccount(account.getId());
                    transaction.setAmount(account.getCurrentAccountBalance());
                    transaction.setTransactionType("C");
                    ResponseEntity<String> responce = proxy.create(transaction);
                }
                logger.info("Processed Adding the Account for Customer id " + optionalCustomer.get().getFirstName());
                return ResponseEntity.ok().body(account.toString());
            }
        }
        return new ResponseEntity<>(
                "Customer not found",
                HttpStatus.BAD_REQUEST);

    }

    @PutMapping(path = {Constants.ACCOUNTS_UPDATE})
    public ResponseEntity<Account> update(@PathVariable("id") long id,
                                          @RequestBody Account account) {
        return repository.findById(id)
                .map(record -> {
                    record.setAccountType(account.getAccountType());
                    record.setCurrentAccountBalance(account.getCurrentAccountBalance());
                    record.setCustomer(account.getCustomer());
                    Account updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {Constants.ACCOUNTS_DELETE})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    // CRUD methods here
}

