package com.example.lawyerlysandbox.accountedgejvm;

public class User {

  private Integer id;
  private String name;
  private Boolean isAdmin;
  private Boolean futureFeatureFlag;

  public User() {
  }

  public User(Integer id, String name, Boolean isAdmin, Boolean futureFeatureFlag) {
    this.id = id;
    this.name = name;
    this.isAdmin = isAdmin;
    this.futureFeatureFlag = futureFeatureFlag;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public Boolean getFutureFeatureFlag() {
    return futureFeatureFlag;
  }

  public void setFutureFeatureFlag(Boolean futureFeatureFlag) {
    this.futureFeatureFlag = futureFeatureFlag;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", isAdmin=" + isAdmin + ", futureFeatureFlag=" + futureFeatureFlag
        + "]";
  }

}
