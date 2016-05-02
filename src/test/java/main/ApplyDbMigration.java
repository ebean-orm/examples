package main;

import com.avaje.ebean.Ebean;

public class ApplyDbMigration {


  public static void main(String[] args) {

    System.setProperty("disableTestProperties", "true");

    Ebean.getDefaultServer();

    System.out.println("done");
  }

}
