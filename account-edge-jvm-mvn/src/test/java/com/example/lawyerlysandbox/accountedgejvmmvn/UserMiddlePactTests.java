package com.example.lawyerlysandbox.accountedgejvmmvn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.*;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;

public class UserMiddlePactTests {

  @Rule
  public PactProviderRule mockProvider = new PactProviderRule("user-middle-jvm", "localhost", 8080, this);

  @Pact(provider = "user-middle-jvm", consumer = "account-edge-jvm")
  public PactFragment createFragment(PactDslWithProvider builder) {

    final Map<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "application/json;charset=UTF-8");

    final PactDslJsonBody bodyResponse = new PactDslJsonBody().
        integerType("id", 2).
        stringValue("name", "User");

    return builder.
        uponReceiving("a request for User").
        path("/user/2").
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

    get("/user/2").

    then().
    assertThat().

    body("id", instanceOf(Integer.class)).and().
    body("id", equalTo(2)).and().
    body("name", instanceOf(String.class)).and().
    body("name", is(notNullValue()));
  }

}
