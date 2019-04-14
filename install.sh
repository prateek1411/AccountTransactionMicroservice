#!/bin/bash

cd microserviceDNS
mvn clean install
cd ..
screen -c screenrc -d -m -S microserviceDNS -t blueharvest_screen java -jar `find ./ -name  microserviceDns*.jar -print | head -n 1` 

cd transactionLedgerService
mvn clean install
cd ..
screen -c screenrc -d -m -S transaction_service_8081 -t blueharvest_screen java -jar -Dserver.port=8081 `find ./ -name  transactionLedgerService*.jar -print | head -n 1`

cd transactionLedgerService
mvn clean install
cd ..
screen -c screenrc -d -m -S transaction_service_8082 -t blueharvest_screen java -jar -Dserver.port=8082 `find ./ -name  transactionLedgerService*.jar -print | head -n 1` 

cd accountService
mvn clean install
cd ..
screen -c screenrc -d -m -S account-service -t blueharvest_screen java -jar -Dserver.port=8000 `find ./ -name  accountService*.jar -print | head -n 1`


