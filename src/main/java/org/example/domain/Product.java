package org.example.domain;

import org.example.domain.finder.ProductFinder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Product entity bean.
 */
@Entity
@Table(name = "o_product")
public class Product {

  public static final ProductFinder find = new ProductFinder();

  @Id
  Long id;

  @Size(max = 20)
  String sku;

  String name;

  public Product(String sku, String name) {
    this.sku = sku;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Return sku.
   */
  public String getSku() {
    return sku;
  }

  /**
   * Set sku.
   */
  public void setSku(String sku) {
    this.sku = sku;
  }

  /**
   * Return name.
   */
  public String getName() {
    return name;
  }

  /**
   * Set name.
   */
  public void setName(String name) {
    this.name = name;
  }

}
