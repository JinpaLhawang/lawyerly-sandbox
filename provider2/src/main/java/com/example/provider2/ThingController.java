package com.example.provider2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThingController {

  @GetMapping(path = "thing/{id}")
  public Thing thing(@PathVariable("id") String id) {
    return new Thing(id, "Name", "MultipleAppsUse", "OneAppUses", "NoAppUses");
  }

}
