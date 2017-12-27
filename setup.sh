#!/bin/bash

echo "Building and Publishing Contracts to Broker..."

cd $LAWYERLY_SANDBOX/account-edge-jvm-mvn/
./mvnw clean install
./mvnw pact:publish

cd $LAWYERLY_SANDBOX/orders-edge-jvm/
./gradlew clean build
./gradlew pactPublish

cd $LAWYERLY_SANDBOX/public-ui-js/
npm install
npm run test:pact:consumer
npm run test:pact:publish

cd $LAWYERLY_SANDBOX/admin-ui-js/
npm install
npm run test:pact:consumer
npm run test:pact:publish
