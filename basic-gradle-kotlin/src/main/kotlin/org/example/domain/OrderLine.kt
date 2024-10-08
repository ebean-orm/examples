package org.example.domain

import org.example.domain.finder.OrderLineFinder
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class OrderLine(

  @ManyToOne
  var product: Product,

  var quantity: Int

) : BaseModel() {

  @ManyToOne(optional = false)
  var order: Order? = null

  var description: String? = null


  companion object Find : OrderLineFinder()
}
