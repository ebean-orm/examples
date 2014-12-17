package org.example.domain;

import com.avaje.ebean.QueryIterator;
import org.example.ExampleBaseTestCase;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

public class LargeQueryWithFindIterateTest extends ExampleBaseTestCase {


  @Test
  public void testFindIterate() {

    BaseModel.class.getName();

    // insert 1000 customers
    int j = 0;
    for (int i = 0; i < 10; i++) {
      Customer customer = new Customer();
      customer.setName("Hello" + j++);
      customer.save();
    }


    AtomicLong counter = new AtomicLong();

    Customer.find
            .select("id, name")
            .where().le("id", 8)
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

    if (true) {
      return;
    }

    QueryIterator<Customer> iterate =
            Customer.find
                    .query().select("id") //.fetch("contacts", new FetchConfig().lazy(20))
                    .findIterate();

    try {

      while (iterate.hasNext()) {
        Customer customer = iterate.next();
        // do something interesting with customer
        //customer.getContacts().size();
        System.out.println("got name " + customer.getId() + " " + customer.getName());
      }

    } finally {
      // close the underlying resources held by the QueryIterator
      // those are:  ResultSet and Connection
      iterate.close();
    }

  }


}
