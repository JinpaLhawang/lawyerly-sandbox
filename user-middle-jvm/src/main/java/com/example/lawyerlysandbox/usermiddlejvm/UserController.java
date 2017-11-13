package com.example.lawyerlysandbox.usermiddlejvm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping(path = "user/{id}")
  public User user(@PathVariable("id") Integer id) {
    if (id.equals(1)) {
      return new User(id, "Admin", true, false);
    }
    return new User(id, "User", false, false);
  }

}
