package org.example.domain;

import io.ebean.AcquireLockException;
import io.ebean.Ebean;
import io.ebean.Query;
import io.ebean.Transaction;
import org.example.domain.query.QProduct;
import org.example.service.LoadExampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

  private static final Logger log = LoggerFactory.getLogger(ProductTest.class);

  @Test
  public void findSome() {

    LoadExampleData.load();

    List<Product> products = new QProduct()
        .name.istartsWith("c")
        .id.greaterThan(0)
        .sku.icontains("c")
        .findList();

    products.size();
  }

  // Run using Postgres with forUpdateNoWait
  @Test(enabled = false)
  public void checkLockRelease() {

    LoadExampleData.load();

    Transaction txn0 = Ebean.beginTransaction();


    try {
      Product newProd = new Product("Copper", "COP");
      Ebean.save(newProd);

      List<Product> list = Product.find.where()
          .name.istartsWith("c")
          .orderBy()
            .name.asc()
          .setForUpdate(true)
          .findList();

      Product firstProduct = list.get(0);

      Query<Product> queryById =
          Product.find.where()
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

    } finally {
      txn0.end();
    }
  }

  private boolean obtainWithLock(Query<Product> queryById) {
    Transaction otherTxn1 = Ebean.getDefaultServer().createTransaction();
    try {

      Product other = Ebean.getDefaultServer().findOne(queryById, otherTxn1);
      other.getName();
      return true;

    } catch (AcquireLockException e) {
      log.info("Failed to obtain lock" + e.getMessage());
      return false;

    } finally {
      otherTxn1.end();
    }
  }
}