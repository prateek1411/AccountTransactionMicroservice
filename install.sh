#!/bin/bash

screen -c screenrc -d -m -S microserviceDNS -t blueharvest_screen java -jar ./microserviceDns/.m2/microserviceDns/microserviceDns/1.0-SNAPSHOT/microserviceDns-1.0-SNAPSHOT.jar 
screen -c screenrc -d -m -S transaction_service_8081 -t blueharvest_screen java -jar -Dserver.port=8081 ./transactionLedgerService/.m2/transactionLedgerService/transactionLedgerService/1.0-SNAPSHOT/transactionLedgerService-1.0-SNAPSHOT.jar
screen -c screenrc -d -m -S transaction_service_8082 -t blueharvest_screen java -jar -Dserver.port=8082 ./transactionLedgerService/.m2/transactionLedgerService/transactionLedgerService/1.0-SNAPSHOT/transactionLedgerService-1.0-SNAPSHOT.jar
screen -c screenrc -d -m -S account-service -t blueharvest_screen java -jar -Dserver.port=8000 ./accountService/.m2/current-account-ms/accountService/1.0-SNAPSHOT/accountService-1.0-SNAPSHOT.jar


