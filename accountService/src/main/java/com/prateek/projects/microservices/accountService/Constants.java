package com.prateek.projects.microservices.accountService;

public final class Constants {

    public static final String API_VERSION = "/api/v1";
    //Accounts CRUD API
    public static final String ACCOUNTS_GET_ALL = API_VERSION + "/accounts";
    public static final String ACCOUNTS_GET = API_VERSION + "/accounts/{id}";
    public static final String ACCOUNTS_ADD = API_VERSION + "/accounts";
    public static final String ACCOUNTS_UPDATE = API_VERSION + "/accounts/{id}";
    public static final String ACCOUNTS_DELETE = API_VERSION + "/accounts/{id}";

    //Customer CRUD API
    public static final String CUSTOMER_GET_ALL = API_VERSION + "/customers";
    public static final String CUSTOMER_GET = API_VERSION + "/customers/{id}";
    public static final String CUSTOMER_ADD = API_VERSION + "/customers";
    public static final String CUSTOMER_UPDATE = API_VERSION + "/customers/{id}";
    public static final String CUSTOMER_DELETE = API_VERSION + "/customers/{id}";

    //Transaction Ledger API
    public static final String TRANSACTION_GET_ALL_ACCOUNT = API_VERSION + "/transaction/account/{id}";
    public static final String TRANSACTION_ADD = API_VERSION + "/transaction";
    public static final String TRANSACTION_GET_BALANCE = API_VERSION + "/transaction/accountBalance/{id}";
}
