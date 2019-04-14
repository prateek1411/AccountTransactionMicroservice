#!/bin/bash

#############
# TEST ACCOUNT ADD API
# TO TEST ACCOUNT API WE NEED to ADD CUSTOMER
###########

echo ******************Adding Customer****************************
echo 
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"firstName":"Prateek","lastName":"Srivastava"}' \
  http://localhost:8000/api/v1/customers
echo 
echo ******************Adding Accounts with 0 initial Amount****************************
echo 
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"accountType":"Current","currentAccountBalance":"20","customer":"1"}' \
  http://localhost:8000/api/v1/accounts
echo 

echo ******************Getting Accounts with Transactions****************************
echo 
curl --header "Content-Type: application/json" \
  --request GET \
  --data '{"accountType":"Current","currentAccountBalance":"20","customer":"1"}' \
  http://localhost:8000/api/v1/transaction/account/2
echo 
