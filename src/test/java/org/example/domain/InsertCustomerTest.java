package org.example.domain;

import io.ebean.Ebean;
import io.ebean.Transaction;
import org.example.ExampleBaseTestCase;
import org.example.service.LoadExampleData;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class InsertCustomerTest extends ExampleBaseTestCase {


  @Test
  public void test() {

    Customer customer = new Customer("Robin");
    customer.setRegistered( LocalDate.of(2014, 4, 1));
    customer.getUids().add(UUID.randomUUID());
    customer.getUids().add(UUID.randomUUID());
    customer.getUids().add(UUID.randomUUID());

    // insert the customer
    customer.save();

    Customer one = Customer.find.query().setId(customer.getId()).forUpdate().findOne();

    Customer fetched = Customer.find.byId(customer.getId());

    // fetch using the Ebean singleton style
    Customer fetched2 = Ebean.find(Customer.class, customer.getId());

    assertEquals("Robin", fetched.getName());
    assertEquals("Robin", fetched2.getName());
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

      Customer customer = new Customer("Roberto");
      customer.save();

      Customer otherCustomer = new Customer("Franko");
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

      Customer customer = new Customer("Roberto2");
      customer.save();

      transaction.setBatchFlushOnQuery(false);

      Customer otherCustomer = new Customer("Franko2");
      otherCustomer.save();

      transaction.commit();

    } finally {
      transaction.end();
    }
  }

  @Test
  public void testQuery() {

    LoadExampleData.load();

    List<Customer> customers =
          Customer.find.where()
            .name.ilike("rob%")
            .findList();

      assertNotNull(customers);

    Product p = new Product("ad", "asd");
    Ebean.save(p);
  }

}
