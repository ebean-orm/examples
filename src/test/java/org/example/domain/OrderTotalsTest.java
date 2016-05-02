package org.example.domain;

import org.example.service.LoadExampleData;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class OrderTotalsTest {

  @Test
  public void basicFind() {

    LoadExampleData.load();

    List<OrderTotals> orderTotals = OrderTotals.find
        .where()
        .orderTotal.greaterThan(20)
        .findList();

    assertThat(orderTotals).isNotEmpty();
  }

  @Test
  public void fetchJoin() {

    LoadExampleData.load();

    List<OrderTotals> orderTotals = OrderTotals.find
        .where()
        .orderTotal.greaterThan(20)
        .fetch("order", "status")
        .fetch("order.details")
        .findList();

    assertThat(orderTotals).isNotEmpty();
  }
}
