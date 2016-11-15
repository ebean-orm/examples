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
  UUID id;

  @Size(max = 20)
  String sku;

  String name;

  public Product() {
  }

  public Product(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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
