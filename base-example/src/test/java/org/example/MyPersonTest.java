package org.example;

import org.assertj.core.api.Assertions;
import org.example.query.QMyPerson;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyPersonTest {

  @Test
  void insertUpdate() {

    var ma = new MyAddress();
    ma.line1 = "line1";
    ma.city = "Auckland";

    var mp = new MyPerson();
    mp.name = "one";
    mp.foo = "foo";
    mp.address = ma;
    mp.save();

    mp.name = "two";
    mp.save();

    MyPerson one = new QMyPerson()
      .id.eq(mp.id)
      .findOne();

    Assertions.assertThat(one).isNotNull();

    String city = one.address.city;
    assertThat(city).isEqualTo("Auckland");
  }
}
