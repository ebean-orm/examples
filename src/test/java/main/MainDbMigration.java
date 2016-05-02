package main;

import com.avaje.ebean.config.dbplatform.DbPlatformName;
import com.avaje.ebean.dbmigration.DbMigration;

import java.io.IOException;

/**
 * Generate the DB Migration.
 */
public class MainDbMigration {

  public static void main(String[] args) throws IOException, InterruptedException {

    // optionally specify the version and name
    System.setProperty("ddl.migration.version", "2.0");
    System.setProperty("ddl.migration.name", "add bars");

    // generate a migration using drops from a prior version
//    System.setProperty("ddl.migration.pendingDropsFor", "1.3");

    DbMigration dbMigration = new DbMigration();
    dbMigration.setPlatform(DbPlatformName.POSTGRES);
    // generate the migration ddl and xml
    dbMigration.generateMigration();
  }
}