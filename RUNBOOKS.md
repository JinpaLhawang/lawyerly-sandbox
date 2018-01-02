RUNBOOKS
========

Glossary
--------

Provider (service that provides an endpoint)
Consumer (service that consumes a Provider's endpoint)
ContractCode (code that details endpoint and required fields; not the actual Contract)
Contract (the actual contract, generated from ContractCode; specifies endpoint, sample request and expected response)
Broker (intermediary that receives and holds Contracts from Consumers; provides Contracts to Providers for verification)
CreateContract (Consumer action that takes ContractCode and generates Contract in JSON format)
PublishContract (Consumer action that takes Contract and Publishes to Broker)
VerifyContract (Provider action that takes a Contract and verifies sample request responds with expected response)

Provider-Driven Approach (Provider Team Writes Endpoint First)
------------------------

This approach is probably more familiar. Provider creates endpoints and documents how to call and what they return ideally using Swagger. Ask what the backend can provide.

1. Provider Team codes endpoint and deploys
2. After an arbitrary period of time, Consumer Team comes along...
3. Consumer Team refers to Swagger Docs to determine how to call and which fields are available
4. Consumer Team writes Contract detailing only those fields it requires
5. Consumer Team runs CreateContract locally, verifying Contract Code successfully creates Contract
6. Consumer Team submits PR (no actual client code yet, just get it locked down; include Provider Team on PR)
7. Consumer and Provider Teams approve PR and merge code
8. CI/CD Pipeline runs build, unit test, ..., CreateContract and PublishContract (now agreement between Consumer and Provider Teams is codified to provide enforcement)

Having codified agreement between Consumer and Provider:

* Consumer Team can write client code that uses endpoint (they don't particularly need to rush to get their code in; and they can rest knowing the endpoint won't change on them)
* Provider Team can iterate on the endpoint, making Non-Contract Breaking Changes and continuously verify endpoint honors Contract
* In event Provider Team attempts to make Contract Breaking Change, VerifyContract action will fail (whether done locally or in CI/CD Pipeline)
* In event Provider Team actually needs to make a Contract Breaking Change, then they know need to contact and negotiate with Consumer Team BEFORE making the change
* OR Provider Team can create a new versioned endpoint...

Consumer-Driven Approach (Consumer Team Writes Contract First)
------------------------

This approach is the flip... but it would be business requirement driven! Consumers drive the conversation of what they need! Tell me what you need!

The Consumer Team could potentially identify and write a Contract for a service that doesn't even exist! That's a bit out there, but we're driving from the Consumer, and we're talking workflow, so... but let's bring it back to Earth, so instead...

1. Consumer Team contacts and negotiates with Provider Team on a new endpoint, the fields required and the expected response (get as specific about types and formats as possible)
2. Consumer Team codifies agreement in ContractCode and submits PR (include Provider Team on PR)
3. Consumer and Provider Teams approve PR and merge code
4. CI/CD Pipeline runs build, unit test, ..., CreateContract and PublishContract (now agreement between Consumer and Provider Teams is codified to provide enforcement)

Having codified agreement between Consumer and Provider:

* Provider Team writes endpoint (potentially mocking/hard-coding response), executing VerifyContract action locally until passes and submits PR (could include Consumer Team on PR, but doesn't need to)
* Consumer Team can write client code that uses endpoint (they don't particularly need to wait for the endpoint to even be written, since they essentially have their agreed upon and enforced mock!)

REALITY?
-------- 

It's probably something more like Provider-Driven Approach, but with more negotiating. And this is where Consumer-Driven Contracts/Contract Testing is important. It helps to promote and facilitate COMMUNICATION between teams. Failing Contracts point out which Consumer expects something different. Needing to make a Contract Breaking Change requires Provider Team to contact and negotiate beforehand. If a Consumer Team erroneously or purposely publishes a Contract (or a change to an existing Contract) against a Provider that they don't know about and causes the Provider to fail, the Provider knows whom to contact... and they can bring the appropriate level of emotional charge to bear!

Already Established Provider and Consumer
-----------------------------------------

- Consumer crawls through its code and writes Contract based specifically on what it requires
- Consumer Deploys, thus Publishing Contract to Broker
- Provider Deploys, verifying Contract continues to be honored

Contracts Established; Provider Makes Non-Contract Breaking Change
------------------------------------------------------------------

- Provider runs PactVerify locally, passes
- Provider writes code
- Provider re-runs PactVerify locally, passes
- Provider Deploys

Contracts Established; Provider Needs to Make Contract Breaking Change
----------------------------------------------------------------------

- Provider runs PactVerify locally, passes
- Provider writes code
- Provider re-runs PactVerify locally, FAILS
- Provider contacts Consumer and negotiates for Consumer to decouple its code and Publish less strict Contract
- Provider re-runs PactVerify locally, passes
- Provider Deploys
