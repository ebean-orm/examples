package org.example.domain;

import io.ebean.FetchGroup;
import org.example.domain.query.QContact;
import org.example.domain.query.QCustomer;
import org.example.domain.query.QOrder;
import org.example.domain.query.QOrderTotals;
import org.example.service.LoadExampleData;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTotalsTest {

  private static final QCustomer cu = QCustomer.alias();
  private static final QContact co = QContact.alias();

  private static final FetchGroup<Customer> fetchGroup = QCustomer.forFetchGroup()
    .select(cu.name, cu.comments)
    .contacts.fetch(co.firstName, co.lastName)
    .buildFetchGroup();

  @Test
  public void basicFind() {

    LoadExampleData.load();

    final List<Customer> rob = new QCustomer()
      .name.startsWith("Rob")
      .select(fetchGroup)
      .findList();


    //FetchGroup<Customer> fg = FetchGroup.of(Customer.class)


    Address billingAddress = new Address();
    billingAddress.setLine1("Not long St");

    Customer jack = new Customer("One");
    jack.setBillingAddress(billingAddress);
//
//    Contact c0 = new Contact(jack, "a", "b");
//    Contact c1 = new Contact(jack, "a", "c");

    jack.save();


    Country nz = Country.find.ref("NZ");

    int rows = new QCustomer()
      .name.startsWith("Rob")
      .asUpdate()
        .set("registered", LocalDate.now())
        .update();


    new QCustomer()
      .alias("cust")
      .name.istartsWith("Rob")
      .findList();


    List<OrderTotals> orderTotals = new QOrderTotals()
        .where()
        .orderTotal.greaterThan(20)
        .findList();

    assertThat(orderTotals).isNotEmpty();
  }

  @Test
  public void fetchJoin() {

    LoadExampleData.load();

    QOrder o = QOrder.alias();
    QCustomer c = QCustomer.alias();

    int rows = new QOrder().findCount();

    List<Order> orders = new QOrder()
      .select(o.status, o.maxOrderDate, o.totalCount)
      .customer.fetch(c.name)
      .status.notEqualTo(Order.Status.COMPLETE)
      .having()
      .totalCount.greaterThan(1)
      .findList();

    LocalDate lastWeek = LocalDate.now().minusWeeks(1);

    // select includes aggregation formula
    List<Order> moreOrders = new QOrder()
      .select("status, max(orderDate)")
      .status.notEqualTo(Order.Status.NEW)
      .findList();

  }
}
