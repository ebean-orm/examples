package org.example.domain;

import io.ebean.DB;
import io.ebean.Transaction;
import io.ebean.test.LoggedSql;
import org.example.ExampleBaseTestCase;
import org.example.domain.query.QCustomer;
import org.example.service.LoadExampleData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InsertCustomerTest extends ExampleBaseTestCase {


  @Test
  public void test() {

    Customer customer = new Customer("Robin");
    customer.setRegistered(LocalDate.of(2014, 4, 1));
    customer.getUids().add(UUID.randomUUID());
    customer.getUids().add(UUID.randomUUID());
    customer.getUids().add(UUID.randomUUID());

    // insert the customer
    customer.save();

    final Customer one = new QCustomer()
      .id.eq(customer.id)
      .forUpdate()
      .findOne();

    Customer fetched = Customer.find.byId(customer.getId());

    // fetch using the Ebean singleton style
    Customer fetched2 = DB.find(Customer.class, customer.getId());

    assertEquals("Robin", fetched.getName());
    assertEquals("Robin", fetched2.getName());
    assertEquals(customer.getRegistered(), fetched2.getRegistered());

    fetched2.setRegistered(LocalDate.now().minusDays(3));
    fetched2.save();
  }

  /**
   * Test showing an explicit transaction.
   */
  @Test
  public void testExplicitTransaction() {

    // create a transaction to wrap multiple saves
    try (Transaction transaction = DB.beginTransaction()) {

      Customer customer = new Customer("Roberto");
      customer.save();

      Customer otherCustomer = new Customer("Franko");
      otherCustomer.save();

      transaction.commit();
    }
  }


  /**
   * Test showing an explicit transaction with extra control
   * the use of jdbc batching.
   */
  @Test
  public void testExplicitTransactionWithBatchControls() {

    try (Transaction transaction = DB.beginTransaction()) {

      // turn off cascade persist for this transaction
      transaction.setPersistCascade(false);

      // extra control over jdbc batching for this transaction
      transaction.setGetGeneratedKeys(false);
      transaction.setBatchMode(true);
      transaction.setBatchSize(20);

      Customer customer = new Customer("Roberto2");
      customer.save();

      transaction.setFlushOnQuery(false);

      Customer otherCustomer = new Customer("Franko2");
      otherCustomer.save();

      transaction.commit();
    }
  }

  @Test
  public void testQuery() {

    LoadExampleData.load();

    LoggedSql.start();

    List<Customer> customers = new QCustomer()
      .name.ilike("rob%")
      .findList();

    assertNotNull(customers);

    Product p = new Product("ad", "asd");
    DB.save(p);

    List<String> sql = LoggedSql.stop();
    assertThat(sql).hasSize(2);
    assertThat(sql.get(0)).contains("from customer");
    assertThat(sql.get(1)).contains("into product");

  }

}
