package com.example.provider1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping(path = "user/{id}")
  public User user(@PathVariable("id") String id) {
    return new User(id, "Name", "MultipleAppsUse", "OneAppUses", "NoAppUses");
  }

}
