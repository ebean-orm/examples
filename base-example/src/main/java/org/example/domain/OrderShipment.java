package org.example.domain;

import org.example.domain.finder.OrderShipmentFinder;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="order_shipment")
public class OrderShipment extends BaseModel {

  public static final OrderShipmentFinder find = new OrderShipmentFinder();

  @ManyToOne(optional = false)
  Order order;

  LocalDate shippedOn;

  String notes;

  public OrderShipment(String notes) {
    this.notes = notes;
    this.shippedOn = LocalDate.now();
  }

  public LocalDate getShippedOn() {
    return shippedOn;
  }

  public void setShippedOn(LocalDate shippedOn) {
    this.shippedOn = shippedOn;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
