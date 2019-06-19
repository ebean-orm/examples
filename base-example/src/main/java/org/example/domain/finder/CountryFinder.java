package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.Country;

public class CountryFinder extends Finder<String, Country> {

  /**
   * Construct using the default EbeanServer.
   */
  public CountryFinder() {
    super(Country.class);
  }

}
