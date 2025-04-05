package org.example.domain;

import io.ebean.annotation.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.domain.finder.CustomerFinder;

import java.time.LocalDate;

@Entity
// DON'T! @Data, @EqualsAndHashCode  See https://ebean.io/docs/best-practice/
@Getter  @Setter
@ToString(doNotUseGetters = true, callSuper = true) // avoid getters!
@NoArgsConstructor  @AllArgsConstructor
//@Accessors(fluent = true, chain = true) // instead of @Builder(toBuilder = true)
public class Customer extends BaseDomain {

  public static final CustomerFinder find = new CustomerFinder();

  @NotNull
  String name;

  LocalDate startDate;

  @Lob
  String comments;

  public Customer(String name) {
    this.name = name;
  }
}
