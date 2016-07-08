package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="o_order_shipment")
public class OrderShipment extends BaseModel {

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
