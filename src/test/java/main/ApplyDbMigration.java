package main;

import io.ebean.Ebean;

public class ApplyDbMigration {


  public static void main(String[] args) {

    // ignore test-ebean.properties
    System.setProperty("disableTestProperties", "true");

    // starting EbeanServer triggers the apply of migrations
    // ... when ebean.migration.run=true
    Ebean.getDefaultServer();

    System.out.println("done");
  }

}
