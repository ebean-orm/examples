package org.example.domain

import org.assertj.core.api.Assertions.assertThat
import org.example.domain.query.QCustomer
import org.example.domain.query.QOrder
import org.junit.Test
import java.math.BigDecimal
import org.example.domain.query.QCustomer.Companion._alias as c

class CustomerTest {

  @Test
  fun someQuery() {

    val customers = QCustomer()
      .select(c.name)
      .name.equalToOrNull("Rob")
      .creditLimit.greaterThan(30_000)
      .orders.isNotEmpty
      .findList()

    assertThat(customers).isEmpty()
  }

  @Test
  fun save() {

    setup()

    val customer = QCustomer()
      .select(c.name)
      .name.eq("Rob")
      .findOne()

    assertThat(customer?.name).isEqualTo("Rob")

    val rob = Customer.findByName("Rob") ?: throw IllegalStateException("no rob")

    assertThat(rob.name).isEqualTo("Rob")

    val products = Product.findMapBySku()

    val order = Order(rob)

    order.lines.add(OrderLine(products["DK1"]!!, 7))
    order.lines.add(OrderLine(products["DK2"]!!, 20))
    order.lines.add(OrderLine(products["CH3"]!!, 1))

    order.save()

    val foundOrder = QOrder()
      .customer.name.startsWith("Ro")
      .findOne()

    assertThat(foundOrder?.customer?.name).isEqualTo("Rob")
  }

  private fun setup() {

    Product("DK1", "Desk").save()
    Product("DK2", "Desk 2").save()
    Product("CH3", "Chair 3").save()

    val customer = Customer("Rob")
    customer.creditLimit = BigDecimal("1000")
    customer.save()
  }
}
