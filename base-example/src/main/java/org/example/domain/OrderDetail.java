package org.example.domain;

import org.example.domain.finder.OrderDetailFinder;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Order Detail entity bean.
 */
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseModel {

  public static final OrderDetailFinder find = new OrderDetailFinder();

  @ManyToOne
  Order order;

  Integer orderQty;

  Integer shipQty;

  Double unitPrice;

  @ManyToOne
  Product product;

  public OrderDetail() {
  }

  public OrderDetail(Product product, Integer orderQty, Double unitPrice) {
    this.product = product;
    this.orderQty = orderQty;
    this.unitPrice = unitPrice;
  }

  /**
   * Return order qty.
   */
  public Integer getOrderQty() {
    return orderQty;
  }

  /**
   * Set order qty.
   */
  public void setOrderQty(Integer orderQty) {
    this.orderQty = orderQty;
  }

  /**
   * Return ship qty.
   */
  public Integer getShipQty() {
    return shipQty;
  }

  /**
   * Set ship qty.
   */
  public void setShipQty(Integer shipQty) {
    this.shipQty = shipQty;
  }

  public Double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
  }

  /**
   * Return order.
   */
  public Order getOrder() {
    return order;
  }

  /**
   * Set order.
   */
  public void setOrder(Order order) {
    this.order = order;
  }

  /**
   * Return product.
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Set product.
   */
  public void setProduct(Product product) {
    this.product = product;
  }

}
