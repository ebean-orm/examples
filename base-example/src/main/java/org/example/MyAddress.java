package org.example;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyAddress {

  @Id
  public long id;
  public String line1;
  public String city;

}
