#!/bin/bash

yellow=`tput setaf 3`
cyan=`tput setaf 6`
reset=`tput sgr0`

echo ""; echo "${cyan}Building and Publishing Contracts to Broker...${reset}"

set -x

echo ""; echo "${yellow}---"
echo "--- account-edge-jvm-mvn ---"
echo "---${reset}"
cd account-edge-jvm-mvn/
echo ""; echo "${cyan}Building Project and Generating Contracts where 'account-edge-jvm-mvn' is a Consumer...${reset}"
./mvnw clean install
echo ""; echo "${cyan}Publishing Contracts where 'account-edge-jvm-mvn' is a Consumer...${reset}"
./mvnw pact:publish

echo ""; echo "${yellow}---"
echo "--- orders-edge-jvm ---"
echo "---${reset}"
cd ../orders-edge-jvm/
echo ""; echo "${cyan}Building Project and Generating Contracts where 'orders-edge-jvm' is a Consumer...${reset}"
./gradlew clean build
echo ""; echo "${cyan}Publishing Contracts where 'orders-edge-jvm' is a Consumer...${reset}"
./gradlew pactPublish

echo ""; echo "${yellow}---"
echo "--- public-ui-js ---"
echo "---${reset}"
cd ../public-ui-js/
npm install
echo ""; echo "${cyan}Generating Contracts where 'public-ui-js' is a Consumer...${reset}"
npm run test:pact:consumer
echo ""; echo "${cyan}Publishing Contracts where 'public-ui-js' is a Consumer...${reset}"
npm run test:pact:publish

echo ""; echo "${yellow}---"
echo "--- admin-ui-js ---"
echo "---${reset}"
cd ../admin-ui-js/
npm install
echo ""; echo "${cyan}Generating Contracts where 'admin-ui-js' is a Consumer...${reset}"
npm run test:pact:consumer
echo ""; echo "${cyan}Publishing Contracts where 'admin-ui-js' is a Consumer...${reset}"
npm run test:pact:publish

echo ""; echo "${cyan}Built and Published Contracts to Pact Broker at http://localhost.${reset}"
