package org.example.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Customer entity bean.
 */
@Entity
@Table(name="be_customer")
public class Customer extends BaseModel {

  /**
   * Convenience Finder for 'active record' style.
   */
  public static final Finder<Long,Customer> find = new Finder<>(Long.class, Customer.class);
  
  boolean inactive;
  
  @Column(length=100)
  String name;

  LocalDate registered;
  
  @Column(length=1000)
  String comments;
  
  @ManyToOne(cascade=CascadeType.ALL)
  Address billingAddress;

  @ManyToOne(cascade=CascadeType.ALL)
  Address shippingAddress;

  @OneToMany(mappedBy="customer", cascade=CascadeType.PERSIST)
  List<Contact> contacts;

  public boolean isInactive() {
    return inactive;
  }

  public void setInactive(boolean inactive) {
    this.inactive = inactive;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getRegistered() {
    return registered;
  }

  public void setRegistered(LocalDate registered) {
    this.registered = registered;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }
  
  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  /**
   * Helper method to add a contact to the customer.
   */
  public void addContact(Contact contact) {
    if (contacts == null) {
      contacts = new ArrayList<>();
    }
    // setting the customer is automatically done when Ebean does
    // a cascade save from customer to contacts. 
    contact.setCustomer(this);
    contacts.add(contact);
  }
  
}
