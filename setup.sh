#!/bin/bash

yellow=`tput setaf 3`
cyan=`tput setaf 6`
reset=`tput sgr0`

echo "${cyan}Building and Publishing Contracts to Broker...${reset}"

echo "${yellow}---"
echo "--- account-edge-jvm-mvn ---"
echo "---${reset}"
cd account-edge-jvm-mvn/
echo "${cyan}Building Project and Generating Contracts where 'account-edge-jvm-mvn' is a Consumer...${reset}"
./mvnw clean install
echo "${cyan}Publishing Contracts where 'account-edge-jvm-mvn' is a Consumer...${reset}"
./mvnw pact:publish

echo "${yellow}---"
echo "--- orders-edge-jvm ---"
echo "---${reset}"
cd ../orders-edge-jvm/
echo "${cyan}Building Project and Generating Contracts where 'orders-edge-jvm' is a Consumer...${reset}"
./gradlew clean build
echo "${cyan}Publishing Contracts where 'orders-edge-jvm' is a Consumer...${reset}"
./gradlew pactPublish

echo "${yellow}---"
echo "--- public-ui-js ---"
echo "---${reset}"
cd ../public-ui-js/
npm install
echo "${cyan}Generating Contracts where 'public-ui-js' is a Consumer...${reset}"
npm run test:pact:consumer
echo "${cyan}Publishing Contracts where 'public-ui-js' is a Consumer...${reset}"
npm run test:pact:publish

echo "${yellow}---"
echo "--- admin-ui-js ---"
echo "---${reset}"
cd ../admin-ui-js/
npm install
echo "${cyan}Generating Contracts where 'admin-ui-js' is a Consumer...${reset}"
npm run test:pact:consumer
echo "${cyan}Publishing Contracts where 'admin-ui-js' is a Consumer...${reset}"
npm run test:pact:publish

echo "${cyan}Built and Published Contracts to Pact Broker at http://localhost.${reset}"
