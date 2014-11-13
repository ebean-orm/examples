package org.example.domain;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.example.ExampleBaseTestCase;
import org.junit.Test;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;

public class InsertCustomerTest extends ExampleBaseTestCase {

  
  @Test
  public void test() {
    
    Customer customer = new Customer();
    customer.setName("Rob");
    customer.setRegistered( LocalDate.of(2014, 4, 1));
    
    // insert the customer
    customer.save();
    
    Customer fetched = Customer.find.byId(customer.getId());

    // fetch using the Ebean singleton style
    Customer fetched2 = Ebean.find(Customer.class, customer.getId());
    
    assertEquals("Rob", fetched.getName());
    assertEquals("Rob", fetched2.getName());
    assertEquals(customer.getRegistered(), fetched2.getRegistered());

  }
  
  
  /**
   * Test showing an explicit transaction.
   */
  @Test
  public void testExplicitTransaction() {
    
    // create a transaction to wrap multiple saves
    Transaction transaction = Customer.db().beginTransaction();
    try {
   
      Customer customer = new Customer();
      customer.setName("Roberto");      
      customer.save();
   
      Customer otherCustomer = new Customer();
      otherCustomer.setName("Franko");      
      otherCustomer.save();
      
      transaction.commit();
      
    } finally {
      // this cleans up the transaction if something 
      // fails in the try block
      transaction.end();
    }
    
  }
  
  
  /**
   * Test showing an explicit transaction with extra control 
   * the use of jdbc batching.
   */
  @Test
  public void testExplicitTransactionWithBatchControls() {
    
    Transaction transaction = Customer.db().beginTransaction();
    try {
      
      // turn off cascade persist for this transaction
      transaction.setPersistCascade(false);
      
      // extra control over jdbc batching for this transaction
      transaction.setBatchGetGeneratedKeys(false);
      transaction.setBatchMode(true);
      transaction.setBatchSize(20);
      
      Customer customer = new Customer();
      customer.setName("Roberto");      
      customer.save();

      transaction.setBatchFlushOnQuery(false);
      
      Customer otherCustomer = new Customer();
      otherCustomer.setName("Franko");      
      otherCustomer.save();
      
      transaction.commit();
      
    } finally {
      transaction.end();
    }
    
  }
  
  
  @Test
  public void testQuery() {
    
      List<Customer> customers = 
          Customer.find.
            where().ilike("name", "rob%")
            .findList();
    
      assertNotNull(customers);
    
  }
  
}
