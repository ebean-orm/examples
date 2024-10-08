package org.example.domain

import io.ebean.Model
import io.ebean.annotation.WhenCreated
import io.ebean.annotation.WhenModified
import java.time.Instant
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version

@MappedSuperclass
open class BaseModel : Model() {

  @Id
  var id: Long = 0

  @Version
  var version: Long = 0

  @WhenCreated
  lateinit var whenCreated: Instant

  @WhenModified
  lateinit var whenModified: Instant
}
