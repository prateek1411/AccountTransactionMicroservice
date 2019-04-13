package com.prateek.projects.microservices.transactionService.serviceProxy;

import com.prateek.projects.microservices.transactionService.Constants;
import com.prateek.projects.microservices.transactionService.model.TransactionLedger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "transaction-serviceProxy", url = "localhost:8081")
public interface TransactionApiServiceProxy {
    @GetMapping(path = Constants.TRANSACTION_GET_ALL_ACCOUNT)
    List<TransactionLedger> listTransactions(@PathVariable("id") Long accountId);
}

