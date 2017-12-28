# Tutorial

## Setup

- Start Local Pact Broker

## Consumer: Read Code

1. Choose a service that calls another service's API.
2. Choose a specific API call.
3. Determine all the runtime dependencies the service has (ie status codes, content type, fields present, specific field names etc)

## Consumer: Write Code

1. Include Pact packages in project.
2. Configure Build Tool (ie Pact Broker URL and any access requirements).
3. Write a contract that tests all the runtime dependencies.
4. Run Build Phase to Generate Contract in JSON.
5. Run Build Phase to Publish Contract to Broker.

## Pact Broker: Explore

- [Home - List of Contracts](http://localhost)
- Click on Page Icons to View Contract
- Click on Services (either Consumers or Providers) to View Dependency Graphs

## Provider: Write Code

1. Include Pact packages in project.
2. Configure Build Tool (ie Pact Broker URL and any access requirements).
3. Run Test Phase to test Contracts.
4. Update API and re-test; break it, fix it, etc

## Workflow: Thought Experiments

- We are effectively injecting tests into another service's test flow... The new yell and scream moment: your service now fails due to a new or changed contract that you didn't know about.
- I need to make a contract breaking change. How would I go about properly making the change?
- I need to write a contract for an endpoint that doesn't exist yet.

## Pact Broker: Further Exploring

- Click on HAL Browser in top right; explore
