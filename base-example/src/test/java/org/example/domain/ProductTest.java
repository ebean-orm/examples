package org.example.domain;

import io.ebean.AcquireLockException;
import io.ebean.DB;
import io.ebean.Query;
import io.ebean.Transaction;
import org.example.domain.query.QProduct;
import org.example.service.LoadExampleData;
import org.junit.jupiter.api.Disabled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

  private static final Logger log = LoggerFactory.getLogger(ProductTest.class);

  @Test
  public void findSome() {

    LoadExampleData.load();

    Product product = Product.byName("Computer");

    List<Product> products = new QProduct()
      .name.istartsWith("c")
      .id.greaterThan(0)
      .sku.icontains("c")
      .findList();

    products.size();
  }

  // Run using Postgres with forUpdateNoWait
  @Disabled
  @Test
  public void checkLockRelease() {

    LoadExampleData.load();

    try (Transaction txn0 = DB.beginTransaction()) {

      Product newProd = new Product("Copper", "COP");
      DB.save(newProd);

      List<Product> list = new QProduct()
        .name.istartsWith("c")
        .orderBy()
        .name.asc()
        .forUpdate()
        .findList();

      Product firstProduct = list.get(0);

      Query<Product> queryById = new QProduct()
        .id.eq(firstProduct.getId())
        .forUpdateNoWait()
        .query();

//          Ebean.findNative(Product.class, "select * from product where id = :id for update nowait")
//          .setParameter("id", firstProduct.getId());

      assertThat(obtainWithLock(queryById)).isFalse();

      try {
        txn0.getConnection().commit();
        //txn0.commitAndContinue();
      } catch (SQLException e) {
        e.printStackTrace();
      }

      assertThat(obtainWithLock(queryById)).isTrue();

      txn0.commit();
    }
  }

  private boolean obtainWithLock(Query<Product> queryById) {

    try (Transaction otherTxn1 = DB.getDefault().createTransaction()) {

      Product other = DB.getDefault().extended().findOne(queryById, otherTxn1);
      other.getName();
      return true;

    } catch (AcquireLockException e) {
      log.info("Failed to obtain lock" + e.getMessage());
      return false;
    }
  }
}
