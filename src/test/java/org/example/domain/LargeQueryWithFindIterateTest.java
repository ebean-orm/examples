package org.example.domain;

import org.example.ExampleBaseTestCase;
import org.junit.Test;

import com.avaje.ebean.QueryIterator;

public class LargeQueryWithFindIterateTest extends ExampleBaseTestCase {

  @Test
  public void testFindIterate() {

    // insert 1000 customers
    int j = 0;
    for (int i = 0; i < 1000; i++) {
      Customer customer = new Customer();
      customer.setName("Hello"+j++);
      customer.save();      
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
        System.out.println("got name "+customer.getId()+" "+customer.getName());
      }
      
    } finally {
      // close the underlying resources held by the QueryIterator
      // those are:  ResultSet and Connection
      iterate.close();
    }

  }


}
