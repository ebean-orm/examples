package org.example.service;

import io.ebean.annotation.Transactional;
import org.example.domain.Address;
import org.example.domain.query.QAddress;


import java.util.ArrayList;
import java.util.List;

public class FooOne {

  public List<Address> findThemBe() {
    return new ArrayList<>();
  }

  public List<Address> findThem() {
    return new QAddress()
      .findList();
  }

  @Transactional
  public List<Address> findThemTxn() {
    return new QAddress()
      //.city.istartsWith("auck")
      .findList();
  }
}
