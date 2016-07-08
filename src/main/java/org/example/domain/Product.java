package org.example.domain;

import org.example.domain.finder.ProductFinder;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Product entity bean.
 */
@Entity
@Table(name = "o_product")
public class Product extends BaseModel {

  public static final ProductFinder find = new ProductFinder();

  @Size(max = 20)
  String sku;

  String name;

  public Product() {
  }

  public Product(Long id) {
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
