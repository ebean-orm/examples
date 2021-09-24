package org.example.domain;


import org.example.domain.query.QCustomer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class CustomerTest {

  @Test
  void queryBean() {

    Customer customer = new Customer("Bar");
    customer.save();

    Customer found = new QCustomer()
      .name.istartsWith("ba")
      .findOne();

    System.out.println("found:" + found);
  }

  @Test
  void saveAndFind() {

    Customer customer = new Customer("hello");
    customer.setStartDate(LocalDate.now());
    customer.setComments("What is this good for?");
    customer.save();

    assertThat(customer.getId()).isGreaterThan(0);

    Optional<Customer> found = Customer.find.byIdOptional(customer.getId());

    assertTrue(found.isPresent());
    found.ifPresent(it -> {
        assertEquals(it.getId(), customer.getId());
        assertEquals(it.getName(), customer.getName());
      }
    );

    Customer hello = Customer.find.findByName("hello");
    assertThat(hello).isNotNull();

    new QCustomer()
      .id.isNotNull()
      .findEach(it -> {
        System.out.println("hello " + it.getName());
        System.out.println(".. started on: " + it.getStartDate());
      });

  }

}
