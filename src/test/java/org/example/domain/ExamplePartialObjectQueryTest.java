package org.example.domain;

import org.example.ExampleBaseTestCase;
import org.testng.annotations.Test;

import java.util.List;

public class ExamplePartialObjectQueryTest extends ExampleBaseTestCase {

  @Test
  public void test() {

    Customer customer =
       Customer.find
           .select("name, email")
           .id.eq(12)
           .findOne();
  }

  @Test
  public void automaticallyAddJoins() {

    Country nz = Country.find.ref("NZ");

    List<Customer> customers =
      Customer.find
        .where()
        .billingAddress.country.equalTo(nz)
        .select("name")
        .findList();

  }
}
