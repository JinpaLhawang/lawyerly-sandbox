# Scenarios

1. Edge endpoint is a pass through from middle, just sends requests to middle and then provides that endpoint for UI. UI contract written against edge, how ensure middle doesn't change endpoint?
1. I am developing a consumer. I need to write a contract. I need to ensure that just the fields I need are covered.
1. I am developing a provider. I need to verify I haven't broken my contracts.
1. I need to make a breaking change to an endpoint. I need to communicate this to consumers so that I can make my change.
1. I need to make a breaking change to an endpoint. I need to create a new versioned endpoint.
1. If an app sends a request to itself, write a contract?

# Notes/Questions

1. Spring Cloud Contract seems a bit different, how could integrate?
1. How best to do local development workflow?
1. Integration with Swagger? Benefit to doing or better to keep modular and pipe together.
