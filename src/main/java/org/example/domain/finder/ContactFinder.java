package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.Contact;

public class ContactFinder extends Finder<Long, Contact> {

  /**
   * Construct using the default EbeanServer.
   */
  public ContactFinder() {
    super(Contact.class);
  }
}
