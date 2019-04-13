package com.prateek.projects.microservices.accountService.serviceProxy;

import com.prateek.projects.microservices.accountService.Constants;
import com.prateek.projects.microservices.accountService.model.TransactionLedger;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "transaction-service")
@RibbonClient(name = "transaction-service")
@Service
public interface AccountApiServiceProxy {
    @GetMapping(path = Constants.TRANSACTION_GET_ALL_ACCOUNT)
    List<TransactionLedger> listTransactions(@PathVariable("id") Long accountId);

    @PostMapping(path = {Constants.TRANSACTION_ADD})
    ResponseEntity<String> create(@RequestBody TransactionLedger transactionLedger);

    @GetMapping(path = Constants.TRANSACTION_GET_BALANCE)
    Long getBalance(@PathVariable("id") Long accountId);
}

