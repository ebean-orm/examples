package org.example;

import io.ebean.DB;
import org.example.domain.Contact;
import org.example.domain.Customer;
import org.example.domain.query.QContact;

import java.util.List;

public class Main {

  public static void main(String[] args) {

    System.out.println("running ...");
    DB.getDefault();

    Customer customer = new Customer("cust6");
    customer.save();

    Contact contact = new Contact();
    contact.setFirstName("foo6");
    contact.setCustomer(customer);
    contact.save();

    List<Contact> found = DB.find(Contact.class).findList();
    System.out.println("contacts: " + found);

    List<Contact> contacts = new QContact()
      .firstName.startsWith("foo")
      .findList();
    System.out.println("contacts: " + contacts);

    List<Contact> list2 = DB.findNative(Contact.class, "select * from contract where firstName like :name")
      .setParameter("name", "f%")
      .findList();

    System.out.println("list2 " + list2);

    System.out.println("done");
  }
}
