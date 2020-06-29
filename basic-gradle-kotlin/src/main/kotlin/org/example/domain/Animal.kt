package org.example.domain

import io.ebean.annotation.Length
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class Animal(name: String, owner: Owner) : BaseModel() {

  @Length(100)
  var name :String = name

  @ManyToOne
  var owner: Owner = owner

  var age : Int = 0

  var type : String? = null

  var notes: String? = null
}
