# pact

## Setup

### Terminal 1: Pact Broker

The Pact Broker runs continuously as a service.

```bash
cd broker/
docker-compose build # Build only needed on first setup
docker-compose up -d # -d for detached mode to keep broker running after closing terminal
```

## Create and Publish Pacts to Pact Broker

### Terminal 2: Build JVM Consumer and Publish Pact to Pact Broker

JVM Consumers implement PactFragment classes to specify the expectation they have for services they rely on. Building will create these 'mocks' and gradle `pactPublish` will publish them to the Pact Broker. It is up to each consumer to update the Pact Broker with any expectations they have for services and to update the broker with changes to the pacts. This allows services to reach out to the broker and verify they continue to meet the pacts they have against them.

```bash
cd consumer1/ # also consumer2
./gradlew clean build # Build Service and Generate Pacts
./gradlew pactPublish # Publish Pacts to Pact Broker
```

### Terminal 3: Build NodeJS Consumer and Publish Pact to Pact Broker

```bash
cd consumer3/ # also ui-js
npm install
gulp createPacts # Generate Pact JSON files within pacts directory
gulp publishPacts # Publish Pacts to Pact Broker
# gulp pact will do both createPacts and publishPacts tasks one after another
```

## Developing a Service that is an Endpoint Provider to Consumers

### Terminal 4: Run Provider

The service being developed/tested must be running for the pacts to be verified. It is configured to run on port 8080. This service has the SpringBoot DevTools package included which enables the service to be reloaded when changes are made to the source during development.

```bash
cd provider1/ # also provider2
./gradlew clean build # Build Service
./gradlew bootRun # Start Service
```

### Terminal 5: Verify Pacts from Pact Broker with Running Service

As changes are made, run gradle `pactVerify` to have the service reach out to the Pact Broker to receive all the consumer pacts and verify the pacts are still being met.

```bash
cd provider1/ # also provider2
./gradlew pactVerify
```

## A Service that is both a Provider and a Consumer

### Terminal 6: Showing dependencies for being both Provider and Consumer

```bash
cd edge1/
./gradlew clean build
./gradlew pactPublish
./gradlew bootRun
# And in another terminal
./gradlew pactVerify
```

## Pact Broker Usage

### Deleting 'Pacticipants' and by extension, contracts

```bash
curl -X DELETE http://localhost/pacticipants/consumer1
```
