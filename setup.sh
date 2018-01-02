#!/bin/bash

yellow=`tput setaf 3`
cyan=`tput setaf 6`
reset=`tput sgr0`

echo "${cyan}Building and Publishing Contracts to Broker...${reset}"

tput setaf 6; echo "${yellow}--- account-edge-jvm-mvn ---${reset}"
cd $LAWYERLY_SANDBOX/account-edge-jvm-mvn/
./mvnw clean install
./mvnw pact:publish

tput setaf 6; echo "${yellow}--- orders-edge-jvm ---${reset}"
cd $LAWYERLY_SANDBOX/orders-edge-jvm/
./gradlew clean build
./gradlew pactPublish

tput setaf 6; echo "${yellow}--- public-ui-js ---${reset}"
cd $LAWYERLY_SANDBOX/public-ui-js/
npm install
npm run test:pact:consumer
npm run test:pact:publish

tput setaf 6; echo "${yellow}--- admin-ui-js ---${reset}"
cd $LAWYERLY_SANDBOX/admin-ui-js/
npm install
npm run test:pact:consumer
npm run test:pact:publish

tput setaf 3; echo "${cyan}Built and Published Contracts to Pact Broker at http://localhost.${reset}"
