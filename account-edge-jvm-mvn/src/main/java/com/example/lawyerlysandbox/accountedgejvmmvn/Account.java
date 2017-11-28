package com.example.lawyerlysandbox.accountedgejvmmvn;

import java.util.Set;

public class Account {

  private User user;
  private Set<Preference> preferences;

  public Account() {
  }

  public Account(User user, Set<Preference> preferences) {
    this.user = user;
    this.preferences = preferences;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<Preference> getPreferences() {
    return preferences;
  }

  public void setPreferences(Set<Preference> preferences) {
    this.preferences = preferences;
  }

  @Override
  public String toString() {
    return "Account [user=" + user + ", preferences=" + preferences + "]";
  }

}
