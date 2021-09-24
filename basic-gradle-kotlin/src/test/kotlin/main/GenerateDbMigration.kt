package main

import io.ebean.annotation.Platform
import io.ebean.dbmigration.DbMigration

fun main() {

  val dbMigration = DbMigration.create()
  dbMigration.setPlatform(Platform.POSTGRES)

  dbMigration.generateMigration()
}
