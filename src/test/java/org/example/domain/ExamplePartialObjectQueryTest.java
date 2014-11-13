package org.example.domain;

import com.avaje.ebean.Ebean;
import org.example.ExampleBaseTestCase;
import org.junit.Test;

public class ExamplePartialObjectQueryTest extends ExampleBaseTestCase {

  @Test
  public void test() {

    Customer customer =
       Customer.find.select("name, email")
            .where().idEq(12)
            .findUnique();
  }

  @Test
  public void automaticallyAddJoins() {

    Country country = Ebean.getReference(Country.class, "NZ");
    Customer customer =
      Customer.find
        .select("name")
        .where().eq("billingAddress.country", country)
        .findUnique();


  }
}
