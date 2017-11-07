package com.example.edge1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AccountController {

  @GetMapping(path = "account/{id}")
  public Account account(@PathVariable("id") String id) {

    final RestTemplate restTemplate = new RestTemplate();

    final User user = restTemplate.getForObject("http://localhost:9001/user/" + id, User.class);
    final Thing thing = restTemplate.getForObject("http://localhost:9002/thing/" + id, Thing.class);

    return new Account(user, thing);
  }

}
