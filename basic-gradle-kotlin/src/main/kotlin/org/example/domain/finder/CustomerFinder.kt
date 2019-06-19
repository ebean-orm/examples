package org.example.domain.finder

import io.ebean.Finder
import org.example.domain.Customer
import org.example.domain.query.QCustomer

open class CustomerFinder : Finder<Long,Customer>(Customer::class.java) {

  fun findByName(name: String): Customer? {

    val c = QCustomer._alias

    return QCustomer()
      .select(c.id, c.name)
      .name.istartsWith(name)
      .findOne()
  }
}
