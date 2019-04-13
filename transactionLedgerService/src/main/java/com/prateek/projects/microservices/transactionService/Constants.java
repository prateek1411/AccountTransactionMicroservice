package com.prateek.projects.microservices.transactionService;

public final class Constants {

    public static final String API_VERSION = "/api/v1";

    //Transaction Ledger API
    public static final String TRANSACTION_GET_ALL_ACCOUNT = API_VERSION + "/transaction/account/{id}";
    public static final String TRANSACTION_GET_ALL = API_VERSION + "/transaction";
    public static final String TRANSACTION_GET = API_VERSION + "/transaction/{id}";
    public static final String TRANSACTION_ADD = API_VERSION + "/transaction";
    public static final String TRANSACTION_UPDATE = API_VERSION + "/transaction/{id}";
    public static final String TRANSACTION_DELETE = API_VERSION + "/transaction/{id}";
    public static final String TRANSACTION_GET_BALANCE = API_VERSION + "/transaction/accountBalance/{id}";
}
