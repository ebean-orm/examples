package org.example.domain;

import io.ebean.annotation.DbPartition;
import io.ebean.annotation.NotNull;
import io.ebean.annotation.PartitionMode;
import org.example.domain.finder.PersonFinder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@DbPartition(mode = PartitionMode.DAY, property = "whenOnboard")
@Entity
public class Person extends Model {

  public static final PersonFinder find = new PersonFinder();

  @Id
  long id;

  @NotNull
  String name;

  String title;

  @NotNull
  Instant whenOnboard;

  public Person(String name) {
    this.name = name;
    this.whenOnboard = Instant.now();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
