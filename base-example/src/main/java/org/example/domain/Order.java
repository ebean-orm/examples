package org.example.domain;

import io.ebean.annotation.Aggregation;
import io.ebean.annotation.EnumValue;
import org.example.domain.finder.OrderFinder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Order entity bean.
 */
@Entity
@Table(name = "orders")
public class Order extends BaseModel {

  public static final OrderFinder find = new OrderFinder();

  public enum Status {
    @EnumValue("N")
    NEW,
    @EnumValue("A")
    APPROVED,
    @EnumValue("S")
    SHIPPED,
    @EnumValue("C")
    COMPLETE,
    @EnumValue("F")
    FOO
  }

  @NotNull
  Status status;

  LocalDate orderDate;

  @Aggregation("max(orderDate)")
  LocalDate maxOrderDate;

  @Aggregation("count(*)")
  Long totalCount;

  LocalDate shipDate;

  @ManyToOne(optional = false)
  Customer customer;

  @ManyToOne
  Address shippingAddress;

  @OrderBy("id asc")
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
  List<OrderDetail> details = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
  List<OrderShipment> shipments = new ArrayList<>();

  public Order(Customer customer) {
    this.status = Status.NEW;
    this.customer = customer;
    this.orderDate = LocalDate.now();
  }

  public String toString() {
    return id + " status:" + status + " customer:" + customer;
  }

  /**
   * Return order date.
   */
  public LocalDate getOrderDate() {
    return orderDate;
  }

  /**
   * Set order date.
   */
  public void setOrderDate(LocalDate orderDate) {
    this.orderDate = orderDate;
  }

  /**
   * Return ship date.
   */
  public LocalDate getShipDate() {
    return shipDate;
  }

  /**
   * Set ship date.
   */
  public void setShipDate(LocalDate shipDate) {
    this.shipDate = shipDate;
  }

  /**
   * Return status.
   */
  public Status getStatus() {
    return status;
  }

  /**
   * Set status.
   */
  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * Return customer.
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Set customer.
   */
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  /**
   * Set the customer with their current shipping address.
   */
  public void setCustomerWithShipping(Customer customer) {
    this.customer = customer;
    this.shippingAddress = customer.getShippingAddress();
  }

  public List<OrderShipment> getShipments() {
    return shipments;
  }

  public void setShipments(List<OrderShipment> shipments) {
    this.shipments = shipments;
  }

  /**
   * Return details.
   */
  public List<OrderDetail> getDetails() {
    return details;
  }

  /**
   * Set details.
   */
  public void setDetails(List<OrderDetail> details) {
    this.details = details;
  }

  public void addDetail(OrderDetail detail) {

    if (details == null) {
      details = new ArrayList<OrderDetail>();
    }
    details.add(detail);
  }

  public LocalDate getMaxOrderDate() {
    return maxOrderDate;
  }

  public void setMaxOrderDate(LocalDate maxOrderDate) {
    this.maxOrderDate = maxOrderDate;
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }
}
