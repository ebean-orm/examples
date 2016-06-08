package org.example.domain;

import com.avaje.ebean.BeanState;
import com.avaje.ebean.Ebean;
import org.example.ExampleBaseTestCase;
import org.example.domain.finder.UpdateQuery;
import org.example.domain.query.QCustomer;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CustomerTest extends ExampleBaseTestCase {

  private Customer jack = new Customer("Jack");

  public void update() {
    UpdateQuery<QCustomer> update = Customer.find.update();
    update.set("name", "foo")
      .where()
      .name.contains("foo")
      .delete();
  }

  /**
   * Even though customer name is final Ebean can build a reference
   * bean only populating the id property.
   */
  @Test
  public void insert() {

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

  @Test(dependsOnMethods = "insert")
  public void partialLoad() {

    Customer found = Customer.find.where()
        .name.equalTo("Jack")
        .id.eq(jack.getId())
        .select("id")
        .findUnique();

    assertThat(found).isNotNull();
    BeanState beanState = Ebean.getBeanState(found);
    Set<String> loadedProps = beanState.getLoadedProps();
    assertThat(loadedProps).containsExactly("id", "contacts");

    // invoke lazy loading
    String name = found.getName();
    assertThat(name).isEqualTo("Jack");
    assertThat(beanState.getLoadedProps()).isNull(); // null meaning all properties are loaded
  }
}
