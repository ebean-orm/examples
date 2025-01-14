package org.example.domain;

import org.example.ExampleBaseTestCase;
import org.example.domain.query.QCustomer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExamplePartialObjectQueryTest extends ExampleBaseTestCase {

  @Test
  public void test() {

    Customer customer = new QCustomer()
           .select("name")
           .id.eq(12)
           .findOne();

    Assertions.assertNotNull(customer);
  }

  @Test
  public void automaticallyAddJoins() {

    Country nz = Country.find.ref("NZ");

    List<Customer> customers = new QCustomer()
        .billingAddress.country.equalTo(nz)
        .select("name")
        .findList();
  }
}
