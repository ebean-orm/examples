package org.example.domain

import io.ebean.annotation.Length
import org.example.domain.finder.ProductFinder
import javax.persistence.Entity

@Entity
class Product(

  @Length(20)
  var sku: String,

  @Length(100)
  var name: String

) : BaseModel() {

  companion object Find : ProductFinder()
}
