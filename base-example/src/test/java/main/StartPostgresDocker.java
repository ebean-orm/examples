package main;

import io.ebean.test.containers.PostgresContainer;

public class StartPostgresDocker {

  public static void main(String[] args) {

    PostgresContainer container = PostgresContainer.builder("14")
      .dbName("my_app9")
      .user("my_app9")
      .password("test")
      .build();

    System.out.println("url: " + container.jdbcUrl());

    container.startWithDropCreate();
  }
}
