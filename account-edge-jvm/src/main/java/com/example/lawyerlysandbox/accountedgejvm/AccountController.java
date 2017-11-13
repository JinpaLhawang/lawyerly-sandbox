package com.example.lawyerlysandbox.accountedgejvm;

import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AccountController {

  @GetMapping(path = "account/{id}")
  public Account account(@PathVariable("id") String id) {

    final RestTemplate restTemplate = new RestTemplate();

    final User user = restTemplate.getForObject("http://localhost:9101/user/" + id, User.class);

    final ResponseEntity<Set<Preference>> preferencesResponse = restTemplate.exchange(
        "http://localhost:9102/user/" + id + "/preferences", HttpMethod.GET, null,
        new ParameterizedTypeReference<Set<Preference>>() {
        });
    final Set<Preference> preferences = preferencesResponse.getBody();

    return new Account(user, preferences);
  }

}
