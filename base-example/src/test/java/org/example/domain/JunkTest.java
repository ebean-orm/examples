package org.example.domain;

import io.ebean.FetchGroup;
import org.example.domain.query.QCustomer;
import org.testng.annotations.Test;

import static org.example.domain.query.QContact.Alias.email;
import static org.example.domain.query.QCustomer.Alias.inactive;
import static org.example.domain.query.QCustomer.Alias.name;
import static org.example.domain.query.QCustomer.Alias.registered;

public class JunkTest {

  static final FetchGroup<Customer> fetchGroup = QCustomer.forFetchGroup()
    .select(name, inactive, registered)
    .contacts.fetch(email)
    .buildFetchGroup();

  @Test
  public void doIt() {

    new QCustomer()
      .select(fetchGroup)
      .findList();

    System.out.println("here");
  }
}
