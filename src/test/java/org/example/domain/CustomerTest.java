package org.example.domain;

import com.avaje.ebean.BeanState;
import com.avaje.ebean.Ebean;
import org.example.ExampleBaseTestCase;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CustomerTest extends ExampleBaseTestCase {

  Customer jack = new Customer("Jack");

  /**
   * Even though customer name is final Ebean can build a reference
   * bean only populating the id property.
   */
  @Test
  public void ref_lazyLoad() {

    jack.save();

    // reference bean only has Id populated
    Customer refBean = Customer.find.ref(jack.getId());

    // only Id property is loaded
    BeanState beanState = Ebean.getBeanState(refBean);
    assertThat(beanState.getLoadedProps()).containsExactly("id");

    // invoke lazy loading
    String name = refBean.getName();
    assertThat(name).isEqualTo("Jack");
    assertThat(beanState.getLoadedProps()).isNull(); // null meaning all properties are loaded
  }

  @Test(dependsOnMethods = "ref_lazyLoad")
  public void partialLoad() {

    Customer jack = Customer.find.where()
        .name.equalTo("Jack")
        .select("id")
        .findUnique();

    BeanState beanState = Ebean.getBeanState(jack);
    assertThat(beanState.getLoadedProps()).containsExactly("id", "contacts");

    // invoke lazy loading
    String name = jack.getName();
    assertThat(name).isEqualTo("Jack");
    assertThat(beanState.getLoadedProps()).isNull(); // null meaning all properties are loaded
  }
}
