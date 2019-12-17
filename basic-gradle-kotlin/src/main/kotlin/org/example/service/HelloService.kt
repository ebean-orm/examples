package org.example.service

import org.example.domain.Order
import org.example.domain.query.QOrder

class HelloService {

  fun findAll(): MutableList<Order> {

    return QOrder()
      .version.isNotNull
      .findList()

  }
}
