package com.prateek.projects.microservices.transactionService.api;

import com.prateek.projects.microservices.transactionService.Constants;
import com.prateek.projects.microservices.transactionService.model.TransactionLedger;
import com.prateek.projects.microservices.transactionService.repository.TransactionLedgerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
public class TransactionLedgerApi {

    Logger logger = LoggerFactory.getLogger(TransactionLedgerApi.class);
    @Autowired
    Environment env;
    private TransactionLedgerRepository repository;

    TransactionLedgerApi(TransactionLedgerRepository transactionLedgerRepository) {
        this.repository = transactionLedgerRepository;
    }
    /*
     * Method called by account microservice to get account balance
     */

    @GetMapping(path = Constants.TRANSACTION_GET_BALANCE)
    public Long getBalance(@PathVariable("id") Long accountId) {
        List<TransactionLedger> transactions = repository.findByAccount(accountId);
        Long balance = 0L;
        Iterator<TransactionLedger> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            balance = balance + iterator.next().getAmount();
        }
        logger.info("processed account balance");
        logger.debug("accunt balance of account id " + accountId + " is " + balance);
        logger.debug("Transaction Microservice Port is " + env.getProperty("server.port"));
        return balance;
    }

    /*
     * Method Called by account microservice to get the list of all transaction
     */
    @GetMapping(path = Constants.TRANSACTION_GET_ALL_ACCOUNT)
    public List<TransactionLedger> listTransactions(@PathVariable("id") Long accountId) {
        logger.info("processed List of Transaction");

        return repository.findByAccount(accountId);
    }

    @GetMapping(path = {Constants.TRANSACTION_GET_ALL})
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {Constants.TRANSACTION_GET})
    public ResponseEntity<TransactionLedger> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    /*
     * Method Called by Account microservice to create transaction.
     */

    @PostMapping(path = {Constants.TRANSACTION_ADD})
    public TransactionLedger create(@RequestBody TransactionLedger transactionLedger) {
        logger.info("processed Save Transaction");
        logger.debug("Transaction Microservice Port is " + env.getProperty("server.port"));
        return repository.save(transactionLedger);
    }


    @DeleteMapping(path = {Constants.TRANSACTION_DELETE})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}

