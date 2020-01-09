package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.Customer;
import org.example.domain.query.QCustomer;

import java.util.Optional;

public class CustomerFinder extends Finder<Long,Customer> {

  /**
   * Construct using the default Database.
   */
  public CustomerFinder() {
    super(Customer.class);
  }

  public Optional<Customer> byIdOptional(long id) {
    return query().setId(id).findOneOrEmpty();
  }

  public Customer findByName(String name) {
    return
      new QCustomer()
      .name.eq(name)
      .findOne();
  }
}
