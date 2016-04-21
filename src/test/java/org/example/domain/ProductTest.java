package org.example.domain;

import org.example.domain.query.QProduct;
import org.example.service.LoadExampleData;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTest {

  @Test
  public void findSome() {

    LoadExampleData.load();

    List<Product> products = new QProduct()
        .name.istartsWith("c")
        .findList();

    products.size();


    List<Product> products1 = Product.find.where()
        .name.istartsWith("c")
        .findList();
  }
}