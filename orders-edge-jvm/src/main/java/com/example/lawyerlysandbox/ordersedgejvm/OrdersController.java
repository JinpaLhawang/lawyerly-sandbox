package com.example.lawyerlysandbox.ordersedgejvm;

import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrdersController {

  @GetMapping(path = "user/{userid}/orders")
  public Set<Order> orders(@PathVariable("userid") Integer userId) {

    final RestTemplate restTemplate = new RestTemplate();

    final ResponseEntity<Set<Order>> ordersResponse = restTemplate.exchange(
        "http://localhost:9104/user/" + userId + "/orders", HttpMethod.GET, null,
        new ParameterizedTypeReference<Set<Order>>() {
        });

    return ordersResponse.getBody();
  }

}
