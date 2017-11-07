package com.example.edge1;

public class Account {

  private User user;
  private Thing thing;

  public Account() {
  }

  public Account(User user, Thing thing) {
    this.user = user;
    this.thing = thing;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Thing getThing() {
    return thing;
  }

  public void setThing(Thing thing) {
    this.thing = thing;
  }

  @Override
  public String toString() {
    return "Account [user=" + user + ", thing=" + thing + "]";
  }

}
