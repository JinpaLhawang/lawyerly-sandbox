#!/bin/bash

yellow=`tput setaf 3`
cyan=`tput setaf 6`
reset=`tput sgr0`

echo "${cyan}Building and Publishing Contracts to Broker...${reset}"

echo "${yellow}---"
echo "--- account-edge-jvm-mvn ---"
echo "---${reset}"
cd account-edge-jvm-mvn/
./mvnw clean install
./mvnw pact:publish

echo "${yellow}---"
echo "--- orders-edge-jvm ---"
echo "---${reset}"
cd ../orders-edge-jvm/
./gradlew clean build
./gradlew pactPublish

echo "${yellow}---"
echo "--- public-ui-js ---"
echo "---${reset}"
cd ../public-ui-js/
npm install
npm run test:pact:consumer
npm run test:pact:publish

echo "${yellow}---"
echo "--- admin-ui-js ---"
echo "---${reset}"
cd ../admin-ui-js/
npm install
npm run test:pact:consumer
npm run test:pact:publish

echo "${cyan}Built and Published Contracts to Pact Broker at http://localhost.${reset}"
