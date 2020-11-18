package main;

import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;

/**
 * Generate the DB Migration.
 */
public class GenerateDbMigration {

  public static void main(String[] args) throws Exception {

    DbMigration dbMigration = DbMigration.create();
    dbMigration.setPlatform(Platform.POSTGRES);
//    dbMigration.setPathToResources("foo/bar");

//    dbMigration.setAddForeignKeySkipCheck(true);
//    dbMigration.setLockTimeout(10);

//    dbMigration.addPlatform(Platform.POSTGRES, "pg");
//    dbMigration.addPlatform(Platform.H2, "h2");
//    dbMigration.addPlatform(Platform.MYSQL, "mysql");
//    dbMigration.addPlatform(Platform.CLICKHOUSE, "ch");

    // generate the migration ddl and xml
//    dbMigration.setGeneratePendingDrop("1.3");
    dbMigration.generateMigration();
  }
}
