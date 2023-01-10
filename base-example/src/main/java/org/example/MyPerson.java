package org.example;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import java.time.Instant;

import static javax.persistence.CascadeType.PERSIST;

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
