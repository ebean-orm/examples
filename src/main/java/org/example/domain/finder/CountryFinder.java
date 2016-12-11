package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.Country;
import org.example.domain.query.QCountry;

public class CountryFinder extends Finder<String,Country> {

  /**
   * Construct using the default EbeanServer.
   */
  public CountryFinder() {
    super(Country.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public CountryFinder(String serverName) {
    super(Country.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QCountry where() {
     return new QCountry(db());
  }

  /**
   * Start a new document store query.
   */
  public QCountry text() {
     return new QCountry(db()).text();
  }
}
