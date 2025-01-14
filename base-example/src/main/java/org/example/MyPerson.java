package org.example;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

import java.time.Instant;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity
public class MyPerson extends Model {

  @Id
  public long id;
  public String name;
  public String notes;
  public String foo;

  @ManyToOne(cascade = PERSIST)
  public MyAddress address;

  @WhenCreated
  public Instant whenCreated;
  @WhenModified
  public Instant whenModified;
  @Version
  public long version;

}
