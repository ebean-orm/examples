package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.OrderShipment;

public class OrderShipmentFinder extends Finder<Long, OrderShipment> {

  /**
   * Construct using the default EbeanServer.
   */
  public OrderShipmentFinder() {
    super(OrderShipment.class);
  }

}
