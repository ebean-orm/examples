package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.OrderTotals;
import org.example.domain.query.QOrderTotals;

public class OrderTotalsFinder extends Finder<Long,OrderTotals> {

  /**
   * Construct using the default EbeanServer.
   */
  public OrderTotalsFinder() {
    super(OrderTotals.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public OrderTotalsFinder(String serverName) {
    super(OrderTotals.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QOrderTotals where() {
     return new QOrderTotals(db());
  }

  /**
   * Start a new document store query.
   */
  public QOrderTotals text() {
     return new QOrderTotals(db()).text();
  }
}
