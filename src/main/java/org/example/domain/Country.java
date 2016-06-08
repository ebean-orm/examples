package org.example.domain;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.Cache;
import org.example.domain.finder.CountryFinder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Country entity bean.
 */
@Cache
@Entity
@Table(name = "o_country", schema = "gen")
public class Country extends Model {

  public static final CountryFinder find = new CountryFinder();

  @Id
  @Size(max = 2)
  final String code;

  @Size(max = 60)
  final String name;

  public Country(String code, String name) {
    this.code = code;
    this.name = name;
  }


  public String toString() {
    return code;
  }

  /**
   * Return code.
   */
  public String getCode() {
    return code;
  }

  /**
   * Return name.
   */
  public String getName() {
    return name;
  }

}
