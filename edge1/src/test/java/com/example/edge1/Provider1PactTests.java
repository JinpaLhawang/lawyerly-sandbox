package com.example.edge1;

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

public class Provider1PactTests {

  @Rule
  public PactProviderRule mockProvider = new PactProviderRule("provider1", "localhost", 8080, this);

  @Pact(provider = "provider1", consumer = "edge1")
  public PactFragment createFragment(PactDslWithProvider builder) {

    final Map<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "application/json;charset=UTF-8");

    final PactDslJsonBody bodyResponse = new PactDslJsonBody().
        stringValue("id", "1234567890").
        stringValue("name", "Name").
        stringValue("multipleAppsUse", "MultipleAppsUse").
        stringValue("oneAppUses", "OneAppUses");

    return builder.
        uponReceiving("a request for User").
        path("/user/1234567890").
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

    get("/user/1234567890").

    then().
    assertThat().

    body("id", equalTo("1234567890")).and().
    body("name", instanceOf(String.class)).and().
    body("name", is(notNullValue())).and().
    body("multipleAppsUse", instanceOf(String.class)).and().
    body("multipleAppsUse", is(notNullValue())).and().
    body("oneAppUses", instanceOf(String.class)).and().
    body("oneAppUses", is(notNullValue()));
  }

}
