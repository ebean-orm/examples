package org.example.domain.finder;

import com.avaje.ebean.Finder;
import org.example.domain.Address;
import org.example.domain.query.QAddress;

public class AddressFinder extends Finder<Long,Address> {

  /**
   * Construct using the default EbeanServer.
   */
  public AddressFinder() {
    super(Address.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public AddressFinder(String serverName) {
    super(Address.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QAddress where() {
     return new QAddress(db());
  }

  /**
   * Start a new document store query.
   */
  public QAddress text() {
     return new QAddress(db()).text();
  }
}
