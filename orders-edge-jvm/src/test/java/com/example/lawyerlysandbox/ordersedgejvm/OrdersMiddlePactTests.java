package com.example.lawyerlysandbox.ordersedgejvm;

import static io.restassured.RestAssured.get;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;

public class OrdersMiddlePactTests {

  @Rule
  public PactProviderRule mockProvider = new PactProviderRule("orders-middle-jvm", "localhost", 8080, this);

  @Pact(provider = "orders-middle-jvm", consumer = "orders-edge-jvm")
  public PactFragment createFragment(PactDslWithProvider builder) {

    final Map<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "application/json;charset=UTF-8");

    final PactDslJsonBody bodyResponse = PactDslJsonArray.
        arrayEachLike().
          integerType("id").
          integerType("amount");

    return builder.
        uponReceiving("a request for Orders").
        path("/user/1/orders").
        method(RequestMethod.GET.name()).

        willRespondWith().
        headers(headers).
        status(200).
        body(bodyResponse).

        toFragment();
  }

  @Test
  @PactVerification
  public void runTest() throws IOException {

    get("/user/1/orders").

    then().
    assertThat().

    body("$", hasSize(1));
  }

}
