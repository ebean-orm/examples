package org.example.domain.finder

import io.ebean.Finder
import org.example.domain.Product

open class ProductFinder : Finder<Long, Product>(Product::class.java) {

  fun findMapBySku() : Map<String,Product> {

    return query()
        .setMapKey("sku")
        .findMap()
  }
}
