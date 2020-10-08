package org.example.domain;

import io.ebean.BeanState;
import io.ebean.DB;
import org.example.ExampleBaseTestCase;
import org.example.domain.query.QCustomer;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.example.domain.query.QCustomer.Alias.id;

public class CustomerTest extends ExampleBaseTestCase {

  /**
   * Even though customer name is final Ebean can build a reference
   * bean only populating the id property.
   */
  @Test
  public void insert() {

    Customer jack = new Customer("Jack");
    jack.setPhoneNumber(new PhoneNumber("02134234"));
    jack.save();

    // reference bean only has Id populated
    Customer refBean = Customer.find.ref(jack.getId());

    // only Id property is loaded
    BeanState beanState = DB.getBeanState(refBean);
    assertThat(beanState.getLoadedProps()).containsExactly("id");

    // invoke lazy loading
    String name = refBean.getName();
    assertThat(name).isEqualTo("Jack");
    assertThat(beanState.getLoadedProps()).contains("id", "name", "version");
  }

  @Test//(dependsOnMethods = "insert")
  public void partialLoad() {

    Customer jim = new Customer("Jim");
    jim.setPhoneNumber(new PhoneNumber("234"));
    jim.save();

    Customer found = new QCustomer()
      .name.equalTo("Jim")
      .id.eq(jim.getId())
      .select(id)
      .findOne();

    assertThat(found).isNotNull();
    BeanState beanState = DB.getBeanState(found);
    Set<String> loadedProps = beanState.getLoadedProps();
    assertThat(loadedProps).containsExactly("id", "contacts", "orders");

    // invoke lazy loading
    String name = found.getName();
    assertThat(name).isEqualTo("Jim");
    assertThat(found.getPhoneNumber().getValue()).isEqualTo("234");

    assertThat(beanState.getLoadedProps()).contains("id", "name", "inactive");
  }
}
