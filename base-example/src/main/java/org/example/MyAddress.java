package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MyAddress {

  @Id
  public long id;
  public String line1;
  public String city;

}
