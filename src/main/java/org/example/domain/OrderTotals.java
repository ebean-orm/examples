package org.example.domain;

import com.avaje.ebean.annotation.Cache;
import com.avaje.ebean.annotation.View;
import org.example.domain.finder.OrderTotalsFinder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Cache(enableQueryCache = true)
@Entity
@View(name = "order_agg_vw", dependentTables="o_order")
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
