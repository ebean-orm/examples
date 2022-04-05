package main;

import io.ebean.docker.commands.PostgresConfig;
import io.ebean.docker.commands.PostgresContainer;

public class StartPostgresDocker {

  public static void main(String[] args) {

    PostgresConfig config = new PostgresConfig("14");
    config.setDbName("my_app9");
    config.setUser("my_app9");
    config.setPassword("test");

    System.out.println("url: " + config.jdbcUrl());

    PostgresContainer container = new PostgresContainer(config);
    container.startWithDropCreate();
  }
}
