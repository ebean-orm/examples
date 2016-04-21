package org.example.domain;

import org.example.ExampleBaseTestCase;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicLong;

public class LargeQueryWithFindIterateTest extends ExampleBaseTestCase {

  @Test
  public void testFindIterate() {

    // insert 1000 customers
    int j = 0;
    for (int i = 0; i < 10; i++) {
      Customer customer = new Customer();
      customer.setName("Hello" + j++);
      customer.save();
    }


    AtomicLong counter = new AtomicLong();

    Customer.find
      .where()
        .id.lessThan(8)
        .select("id, name")
        .findEach((Customer customer) -> {
          long processCount = counter.incrementAndGet();
          System.out.println("Hello there2 ... " + customer.getId() + " " + customer.getName() + " counter:" + processCount);
        });


    counter.set(0);

    Customer.find
            .select("id, name")
            .findEachWhile((Customer customer) -> {
              long processCount = counter.incrementAndGet();
              System.out.println("Hello there2 ... " + customer.getId() + " " + customer.getName() + " counter:" + processCount);
              return processCount < 7;
            });

  }


}
