package main

import io.ebean.annotation.Platform
import io.ebean.dbmigration.DbMigration

fun main(args : Array<String>) {

  val dbMigration = DbMigration.create()
  dbMigration.setPlatform(Platform.POSTGRES)

  dbMigration.generateMigration()
}
