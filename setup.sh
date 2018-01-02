#!/bin/bash

tput setaf 3; echo "Building and Publishing Contracts to Broker..."

tput setaf 6; echo "--- account-edge-jvm-mvn ---"
cd $LAWYERLY_SANDBOX/account-edge-jvm-mvn/
./mvnw clean install
./mvnw pact:publish

tput setaf 6; echo "--- orders-edge-jvm ---"
cd $LAWYERLY_SANDBOX/orders-edge-jvm/
./gradlew clean build
./gradlew pactPublish

tput setaf 6; echo "--- public-ui-js ---"
cd $LAWYERLY_SANDBOX/public-ui-js/
npm install
npm run test:pact:consumer
npm run test:pact:publish

tput setaf 6; echo "--- admin-ui-js ---"
cd $LAWYERLY_SANDBOX/admin-ui-js/
npm install
npm run test:pact:consumer
npm run test:pact:publish

tput setaf 3; echo "Built and Published Contracts to Pact Broker at http://localhost."
