#!/bin/bash

yellow=`tput setaf 3`
cyan=`tput setaf 6`
reset=`tput sgr0`

echo ""; echo "${cyan}Building and Publishing Contracts to Broker...${reset}"

printTitle() {
  echo ""; echo "${yellow}---"; echo "--- $1 ---"; echo "---${reset}"
}

printTitle account-edge-jvm-mvn
cd account-edge-jvm-mvn/
echo "${cyan}Building Project and Generating Contracts where 'account-edge-jvm-mvn' is a Consumer...${reset}"
./mvnw clean install
echo ""; echo "${cyan}Publishing Contracts where 'account-edge-jvm-mvn' is a Consumer...${reset}"
./mvnw pact:publish
cd ..

printTitle orders-edge-jvm
cd orders-edge-jvm/
echo "${cyan}Building Project and Generating Contracts where 'orders-edge-jvm' is a Consumer...${reset}"
./gradlew clean build
echo ""; echo "${cyan}Publishing Contracts where 'orders-edge-jvm' is a Consumer...${reset}"
./gradlew pactPublish
cd ..

npmGenerateAndPublishContracts() {
  printTitle $1
  cd $1
  npm install
  echo ""; echo "${cyan}Generating Contracts where '$1' is a Consumer...${reset}"
  npm run test:pact:consumer
  echo ""; echo "${cyan}Publishing Contracts where '$1' is a Consumer...${reset}"
  npm run test:pact:publish
  cd ..
}

npmGenerateAndPublishContracts public-ui-js
npmGenerateAndPublishContracts admin-ui-js

echo ""; echo "${cyan}Built and Published Contracts to Pact Broker at http://localhost.${reset}"
