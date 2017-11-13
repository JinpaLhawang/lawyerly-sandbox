package com.example.lawyerlysandbox.ordersedgejvm;

public class Order {

  private Integer id;
  private Integer amount;

  public Order() {
  }

  public Order(Integer id, Integer amount) {
    this.id = id;
    this.amount = amount;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", amount=" + amount + "]";
  }

}
