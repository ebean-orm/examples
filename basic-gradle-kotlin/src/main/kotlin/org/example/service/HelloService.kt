package org.example.service

import org.example.domain.Order
import org.example.domain.query.QOrder

class HelloService {

  fun findAll(): MutableList<Order> {
    return QOrder()
      .version.isNotNull
      .findList()
  }

  fun findSome() {
    val one = QOrder()
      .version.gt(1)
      .findOne()

    val i = one?.customer?.orders?.size ?: 0;
    println(i)
  }
}
