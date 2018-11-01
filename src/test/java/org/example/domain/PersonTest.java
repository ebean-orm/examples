package org.example.domain;

import org.example.domain.query.QPerson;
import org.testng.annotations.Test;

public class PersonTest {

  @Test
  public void insert() {


//    String tz = Ebean.createSqlQuery("SELECT current_setting('TIMEZONE') as tz")
//      .findOne()
//      .getString("tz");
//
//    System.out.println("tz: "+tz);
//
//    List<SqlRow> list = Ebean.createSqlQuery("select _hello(_partition_meta('month', d, 'a','','')) as one from _partition_over('month', current_date, 3) d;")
//      .findList();
//
//    for (SqlRow sqlRow : list) {
//      System.out.println(sqlRow.getString("one"));
//    }

    Person p = new Person("Rob");
    p.setTitle("Dogs Body");
    p.save();

    p.setTitle("Big Cheese");
    p.save();

    Person rob = new QPerson()
      .name.istartsWith("ro")
      .findOne();

    System.out.println("done");

  }

}
