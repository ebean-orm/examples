package org.example.domain;

import org.example.ExampleBaseTestCase;
import org.testng.annotations.Test;

public class ExamplePartialObjectQueryTest extends ExampleBaseTestCase {

  @Test
  public void test() {

    Customer customer =
       Customer.find
           .select("name, email")
           .id.eq(12)
           .findUnique();
  }

  @Test
  public void automaticallyAddJoins() {

    Country nz = Country.find.ref("NZ");

    Customer customer =
      Customer.find
        .where()
        .billingAddress.country.equalTo(nz)
        .select("name")
        .findUnique();

  }
}
