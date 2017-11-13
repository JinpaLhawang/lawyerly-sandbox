package com.example.lawyerlysandbox.ordersmiddlejvm;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

  @GetMapping(path = "user/{userid}/orders")
  public Set<Order> orders(@PathVariable("userid") Integer userId) {
    final Set<Order> orders = new HashSet<Order>();
    if (userId.equals(1)) {
      orders.add(new Order(1, 123));
      orders.add(new Order(3, 5342));
    } else {
      orders.add(new Order(2, 12));
    }
    return orders;
  }

}
