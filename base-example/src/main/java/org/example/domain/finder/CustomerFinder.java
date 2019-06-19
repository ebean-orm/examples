package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.Customer;

public class CustomerFinder extends Finder<Long,Customer> {

  /**
   * Construct using the default EbeanServer.
   */
  public CustomerFinder() {
    super(Customer.class);
  }

}
