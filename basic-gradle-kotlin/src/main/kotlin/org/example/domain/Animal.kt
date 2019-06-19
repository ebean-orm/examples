package org.example.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class Animal(name: String, owner: Owner) : BaseModel() {

  var name :String = name

  @ManyToOne
  var owner: Owner = owner

  var age : Int = 0
  var type : String? = null

  var notes: String? = null
}
