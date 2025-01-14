package org.example.domain;

import io.ebean.annotation.Cache;
import io.ebean.annotation.View;
import org.example.domain.finder.OrderTotalsFinder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Cache(enableQueryCache = true)
@Entity
@View(name = "order_agg_vw", dependentTables="order")
public class OrderTotals {

  public static final OrderTotalsFinder find = new OrderTotalsFinder();

  @Id
  Long id;

  @OneToOne
  Order order;

  Double orderTotal;

  Double shipTotal;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getOrderTotal() {
    return orderTotal;
  }

  public void setOrderTotal(Double orderTotal) {
    this.orderTotal = orderTotal;
  }

  public Double getShipTotal() {
    return shipTotal;
  }

  public void setShipTotal(Double shipTotal) {
    this.shipTotal = shipTotal;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
