module example.java21 {

  requires io.ebean.postgres;

  opens dbmigration;
  exports org.example.domain;
  opens org.example.domain;

  provides io.ebean.config.ModuleInfoLoader with org.example.domain._Ebean$ModuleInfo;

}
