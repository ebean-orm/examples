package org.example.domain;

import org.example.domain.finder.CustomerFinder;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.time.LocalDate;

@Entity
public class Customer extends BaseDomain {

  public static final CustomerFinder find = new CustomerFinder();

  @NotNull
  String name;

  LocalDate startDate;

  @Lob
  String comments;

  public Customer(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

}
