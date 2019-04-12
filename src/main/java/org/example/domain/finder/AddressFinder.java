package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.Address;

public class AddressFinder extends Finder<Long, Address> {

  /**
   * Construct using the default EbeanServer.
   */
  public AddressFinder() {
    super(Address.class);
  }

}
