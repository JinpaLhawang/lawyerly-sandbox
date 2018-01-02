Contract Testing: Avoiding the Monolith to Distributed Monolith

Problems with Monolith
 - very difficult to assess interactions between components
 - left to add code/endpoints and bug fix, but not really refactor and update, certainly no deleting

So break Monolith into distributed system of microservices
 - still difficult to assess interactions between components
 - still left to add code/endpoints and bug fix, but not really refactor and update, certainly no deleting

Embedded in Consumer code are static JSON files that represent the response at a certain point in time of a particular endpoint it consumes. Not only was the Consumer code based off the static file, unit and IT tests run using it to verify the Consumers code. What if the Provider changed the response? What if the Provider wants/needs to change the response?

The Provider likely does not know which services consume its endpoints. If it wants to change something, it is difficult to predict what will happen. Instead it most likely has a new versioned endpoint created. Additive.

Fine, except over time, still just caught in the Distributed Monolith pattern. The system grows to size that cannot be flexible and fast moving.

Enter contracts.

Those JSON files are still useful, but instead of them being copy/pasted from a sample request/response once upon a time, auto-generate them each time during a build phase. Then share the file with an intermediary service, the Pact Broker, such that both Consumer and Provider can access it.

Also, instead of the JSON file just being a sample response, also embed in it the sample request used.

When Provider executes Test Phase, Provider reaches out to the Pact Broker and asks for All Contracts where it is the Provider. It then runs each of the Contract requests through the service and ensures the response continues to be honored.

Consumer is responsible for Creating and Publishing Contracts to Pact Broker. This is the appropriate place for the responsibility as opposed to the Provider.

If Provider makes a code change that fails the contract, then they need to reach out to Consumer team and negotiate how to proceed.

1. Consumer team makes code change that allows Provider to make their change -> Consumer creates PR with both Consumer and Provider Teams
2. Provider then makes their code change -> Provider creates PR, does not need to include Consumer on PR, just passing Contract Tests is fine enough

But the number 1 benefit is the communication that has occurred between teams. And the Provider has made their code change, AFTER the Consumer has accommodated, thus avoiding the Provider just making the code change and then sometime later, the Consumer figures out that something on their end has broken... and then finally accommodated.

