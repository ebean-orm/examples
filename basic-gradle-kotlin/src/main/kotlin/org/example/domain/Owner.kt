package org.example.domain

import javax.persistence.Entity

@Entity
class Owner(name:String) : BaseModel() {

  val name :String = name

  var phone:String? = null
}
