package org.example.domain;

import io.ebean.FetchGroup;
import io.ebean.annotation.Transactional;
import org.example.domain.query.QCustomer;
import org.example.domain.query.QOrder;
import org.junit.jupiter.api.Test;

import static org.example.domain.query.QContact.Alias.email;
import static org.example.domain.query.QCustomer.Alias.inactive;
import static org.example.domain.query.QCustomer.Alias.name;
import static org.example.domain.query.QCustomer.Alias.registered;

public class JunkTest {

  static final FetchGroup<Customer> fetchGroup = QCustomer.forFetchGroup()
    .select(name, inactive, registered)
    .contacts.fetch(email)
    .buildFetchGroup();

  private final int count;

  JunkTest() {
    count = new QOrder()
      .findCount();
  }
  @Test
  @Transactional
  public void doIt() {

    new QCustomer()
      .select(fetchGroup)
      .findList();

    System.out.println("here v2");
  }
}
