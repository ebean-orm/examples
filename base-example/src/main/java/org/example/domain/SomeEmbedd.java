package org.example.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class SomeEmbedd {

  String one;
  String two;
  String three;

  public String getOne() {
    return one;
  }

  public void setOne(String one) {
    this.one = one;
  }

  public String getTwo() {
    return two;
  }

  public void setTwo(String two) {
    this.two = two;
  }

  public String getThree() {
    return three;
  }

  public void setThree(String three) {
    this.three = three;
  }
}
