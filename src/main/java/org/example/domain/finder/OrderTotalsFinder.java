package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.OrderTotals;

public class OrderTotalsFinder extends Finder<Long, OrderTotals> {

  /**
   * Construct using the default EbeanServer.
   */
  public OrderTotalsFinder() {
    super(OrderTotals.class);
  }

}
