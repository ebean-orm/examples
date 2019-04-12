package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.OrderDetail;

public class OrderDetailFinder extends Finder<Long, OrderDetail> {

  /**
   * Construct using the default EbeanServer.
   */
  public OrderDetailFinder() {
    super(OrderDetail.class);
  }

}
