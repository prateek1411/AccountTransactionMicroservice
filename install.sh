#!/bin/bash

#!/bin/bash

mvn clean install -f microserviceDns/pom.xml
screen -c screenrc -d -m -S microserviceDNS -t blueharvest_screen java -jar `find ./ -name  microserviceDns*.jar -print | head -n 1`

mvn clean install -f transactionLedgerService/pom.xml
screen -c screenrc -d -m -S transaction_service_8081 -t blueharvest_screen java -jar -Dserver.port=8081 `find ./ -name  transactionLedgerService*.jar -print | head -n 1`
screen -c screenrc -d -m -S transaction_service_8082 -t blueharvest_screen java -jar -Dserver.port=8082 `find ./ -name  transactionLedgerService*.jar -print | head -n 1`

mvn clean install -f accountService/pom.xml
screen -c screenrc -d -m -S account-service -t blueharvest_screen java -jar -Dserver.port=8000 `find ./ -name  accountService*.jar -print | head -n 1`
