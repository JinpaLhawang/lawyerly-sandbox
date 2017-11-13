package com.example.lawyerlysandbox.preferencesmiddlejvm;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreferencesController {

  @GetMapping(path = "user/{id}/preferences")
  public Set<Preference> preferences(@PathVariable("id") Integer id) {
    final Set<Preference> preferences = new HashSet<Preference>();
    if (id.equals(1)) {
      preferences.add(new Preference("color", "blue"));
      preferences.add(new Preference("ui", "admin"));
    } else {
      preferences.add(new Preference("color", "red"));
    }
    return preferences;
  }

}
