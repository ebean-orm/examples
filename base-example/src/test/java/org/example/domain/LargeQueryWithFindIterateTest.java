package org.example.domain;

import io.ebean.annotation.Transactional;
import org.example.ExampleBaseTestCase;
import org.example.domain.query.QCustomer;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

public class LargeQueryWithFindIterateTest extends ExampleBaseTestCase {

  @Transactional
  @Test
  public void testFindIterate() {

    // insert 1000 customers
    int j = 0;
    for (int i = 0; i < 10; i++) {
      Customer customer = new Customer("Hello" + j++);
      customer.save();
    }


    AtomicLong counter = new AtomicLong();

    new QCustomer()
      .id.lessThan(8)
      .select("id, name")
      .findEach((Customer customer) -> {
        long processCount = counter.incrementAndGet();
        System.out.println("Hello there2 ... " + customer.getId() + " " + customer.getName() + " counter:" + processCount);
      });


    counter.set(0);

    new QCustomer()
      .select("id, name")
      .findEachWhile((Customer customer) -> {
        long processCount = counter.incrementAndGet();
        System.out.println("Hello there2 ... " + customer.getId() + " " + customer.getName() + " counter:" + processCount);
        return processCount < 7;
      });
  }

}
