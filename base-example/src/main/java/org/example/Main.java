package org.example;

import io.ebean.DB;

public class Main {

  public static void main(String[] args) {

    System.out.println("running ...");
    DB.getDefault();

    System.out.println("done");
  }
}
