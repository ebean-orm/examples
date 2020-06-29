package org.example.domain;

import io.ebean.annotation.EbeanComponent;
import io.ebean.event.BeanPersistAdapter;
import io.ebean.event.BeanPersistRequest;

@EbeanComponent
public class CustomerPersistAdapter extends BeanPersistAdapter {

  @Override
  public boolean isRegisterFor(Class<?> cls) {
    return Customer.class.equals(cls);
  }

  @Override
  public void postInsert(BeanPersistRequest<?> request) {
    final Object bean = request.getBean();
    System.out.println("postInsert " + bean);
  }

  @Override
  public void postUpdate(BeanPersistRequest<?> request) {
    final Object bean = request.getBean();
    System.out.println("postUpdate " + bean + " updated:" + request.getUpdatedProperties() + " dirty:" + request.getUpdatedValues());
  }
}
