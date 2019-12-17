package org.example.service

import org.assertj.core.api.Assertions.assertThat
import org.example.domain.query.QOrder
import org.junit.Test

internal class HelloServiceTest {

  @Test
  fun findAll() {

    val service = HelloService()

    QOrder().delete()

    val list = service.findAll()
    assertThat(list).isEmpty()

  }
}
