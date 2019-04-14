## Transaction and Account Microservice ##

This Microservice exposes two APIs 
1. The API will expose an endpoint which accepts the user information (customerID, initialCredit).
Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID. Also, if initialCredit is not 0, a transaction will be sent to the new account.

2. Another Endpoint will output the user information showing Name, Surname, balance, and transactions of the accounts. 

### Architecture Diagram ###

![alt text](Account-Transsaction-ms-Arch.png)

### Installation ###

##### Pre resiquites To buld and run the Application #### 
1. JDK 1.8 (jdk is required to compile the code)
2. Linux Or Mac OS Bash Shell
3. screen (to open multiple session in background)
4. apache  maven

###Building and  Running the Application ###

**Windows users who dont have screen can perhaps manually build and run**
 - mvn clean install -f microserviceDns/pom.xml
 - java -jar -Dserver.port=\< port number \> \<microserviceDns\*.jar file location\>
 - mvn clean install -f transactionLedgerService/pom.xml
 - java -jar -Dserver.port=\< port number \> \<transactionLedgerService\*.jar file location\>
 - mvn clean install -f accountService/pom.xml
 - java -jar -Dserver.port=\< port number \> \<accountService\*.jar file location\>

**Linux and Mac OS users can run**
> Run install.sh
  - It will open four screen session which will be running the following
    - microserviceDns service 8761
    - account Service in port 8000
    - transaction service in port 8081
    - transaction service in port 8082
### API Specification ###

1. Adding Customer
 - uri : http://localhost:8000/api/v1/customers 
 - method : POST
 - body : {"firstName":"Prateek","lastName":"Srivastava"}

2. Adding Accounts
 - uri: http://localhost:8000/api/v1/accounts
 - method : POST
 - body : {"accountType":"Current","currentAccountBalance":"20","customer":"1"}

3. Listing Customer and Accounts
 - uri : http://localhost:8000/api/v1/transaction/account/{id}
 - method : GET

### Testing ###
Please run testing_services.sh which contain few curl commands to test the service. The service can be also testted usin any rest client like postman
