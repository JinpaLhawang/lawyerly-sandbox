package com.example.edge1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

  private String id;
  private String name;
  private String multipleAppsUse;
  private String oneAppUses;
  private String noAppUses;

  public User() {
  }

  public User(String id, String name, String multipleAppsUse, String oneAppUses, String noAppUses) {
    this.id = id;
    this.name = name;
    this.multipleAppsUse = multipleAppsUse;
    this.oneAppUses = oneAppUses;
    this.noAppUses = noAppUses;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMultipleAppsUse() {
    return multipleAppsUse;
  }

  public void setMultipleAppsUse(String multipleAppsUse) {
    this.multipleAppsUse = multipleAppsUse;
  }

  public String getOneAppUses() {
    return oneAppUses;
  }

  public void setOneAppUses(String oneAppUses) {
    this.oneAppUses = oneAppUses;
  }

  public String getNoAppUses() {
    return noAppUses;
  }

  public void setNoAppUses(String noAppUses) {
    this.noAppUses = noAppUses;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", multipleAppsUse=" + multipleAppsUse + ", oneAppUses=" + oneAppUses
        + ", noAppUses=" + noAppUses + "]";
  }

}
