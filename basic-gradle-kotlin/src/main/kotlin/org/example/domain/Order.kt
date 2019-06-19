package org.example.domain

import org.example.domain.finder.OrderFinder
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name="orders")
class Order(

  @ManyToOne
  val customer: Customer

) : BaseModel() {

  var whenPlacedFor : OffsetDateTime? = null

  var whenInvoiced : OffsetDateTime? = null

  @OneToMany(cascade = [CascadeType.ALL], mappedBy = "order")
  var lines: MutableList<OrderLine> = mutableListOf()

  companion object Find : OrderFinder()
}
