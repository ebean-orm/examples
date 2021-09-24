package org.example.domain

import org.example.domain.query.QAnimal
import org.junit.jupiter.api.Test


class AnimalTest {

  @Test
  fun setName() {

    val allie = Owner("Allie")
    allie.phone = "021777890"
    allie.save()

    val ethan = Owner("Ethan")
    ethan.phone = "021354879"
    ethan.save()


    val pepper = Animal("pepper", allie)
    pepper.age = 2
    pepper.notes = "squashed by garage door"
    pepper.type = "cat"
    pepper.save()

    val foo = Animal("coloso", ethan)
    foo.age = 5
    foo.type = "bunny"
    foo.notes = "saw paw"
    foo.save()

//    val foofoo
    val animals = QAnimal()
      //.name.startsWith("pe")
      .or()
      .owner.name.istartsWith("a")
      .owner.name.istartsWith("e")
      .endOr()
      .age.lessThan(9)
      .findList()

    println("found ${animals.size} animals")

    for (animal in animals) {
      println("found ${animal.name}  ${animal.age} ${animal.type} v${animal.version} owner:${animal.owner.name}${animal.owner.phone}")
    }

//    pepper.name="allie"
    pepper.save()

//    foo.name="ethan"
    foo.save()


    val other = QAnimal()
      .type.isIn("cat", "bunny")
      .notes.isNotNull
      .id.lessThan(3)
      .findList()

    println("Second query size: ${other.size}")

    for (animal in other) {
      println(" ${animal.name} ${animal.notes} id:${animal.id} v${animal.version} ${animal.owner.name}")
    }
  }

}
