package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.Product;

public class ProductFinder extends Finder<Long, Product> {

  /**
   * Construct using the default EbeanServer.
   */
  public ProductFinder() {
    super(Product.class);
  }

}

