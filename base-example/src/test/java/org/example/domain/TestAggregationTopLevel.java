package org.example.domain;

import io.ebean.DB;
import io.ebean.Query;
import io.ebean.test.LoggedSql;
import org.example.domain.query.QDMachineStats;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestAggregationTopLevel {

  @BeforeAll
  public static void setup() {
    loadData();
  }

  @Test
  public void query_noSelect() {

    Query<DMachineStatsAgg> query = DB.find(DMachineStatsAgg.class)
      .where().gt("date", LocalDate.now().minusDays(10))
      .query();

    List<DMachineStatsAgg> result = query.findList();
    // select t0.date, t0.machine_id from d_machine_stats t0 where t0.date > ?");
    assertThat(result).isNotEmpty();
  }

  @Test
  public void query_machineTotalKms_withHaving() {

    Query<DMachineStatsAgg> query = DB.find(DMachineStatsAgg.class)
      .select("machine, date, totalKms, totalCost")
      .where().gt("date", LocalDate.now().minusDays(10))
      .having().gt("totalCost", 10)
      .query();

    List<DMachineStatsAgg> result = query.findList();
    // "select t0.machine_id, t0.date, sum(t0.total_kms), sum(cost) from d_machine_stats t0 where t0.date > ?  group by t0.machine_id, t0.date having sum(cost) > ?");
    assertThat(result).isNotEmpty();
  }

  @Test
  public void query_machineTotalKms() {

    Query<DMachineStatsAgg> query = DB.find(DMachineStatsAgg.class)
      .select("machine, totalKms, totalCost")
      .where().gt("date", LocalDate.now().minusDays(10))
      .query();

    List<DMachineStatsAgg> result = query.findList();
    // "select t0.machine_id, sum(t0.total_kms), sum(cost) from d_machine_stats t0 where t0.date > ?  group by t0.machine_id");
    assertThat(result).isNotEmpty();
  }

  @Test
  public void query_byDate() {

    Query<DMachineStatsAgg> query = DB.find(DMachineStatsAgg.class)
      .select("date, totalKms, hours, rate, totalCost, maxKms")
      .where().gt("date", LocalDate.now().minusDays(10))
      .having().gt("hours", 2)
      .query();

    List<DMachineStatsAgg> result = query.findList();
    // "select t0.date, sum(t0.total_kms), sum(t0.hours), max(t0.rate), sum(cost), max(t0.total_kms) from d_machine_stats t0 where t0.date > ?  group by t0.date having sum(t0.hours) > ?");
    assertThat(result).isNotEmpty();
  }

  @Test
  public void groupBy_date_dynamicFormula() {

    LoggedSql.start();

    List<DMachineStats> result =
      new QDMachineStats()
      .date.gt(LocalDate.now().minusDays(10))
      .select("date, sum(totalKms), sum(hours)")
      .havingClause().gt("sum(hours)", 2)
      .findList();

    List<String> sql = LoggedSql.stop();

    assertThat(sql).hasSize(1);
    assertThat(sql.getFirst()).isNotNull();
    assertThat(result).isNotEmpty();
  }

  @Test
  public void groupBy_MachineAndDate_dynamicFormula() {

    LoggedSql.start();

    List<DMachineStats> result =
      new QDMachineStats()
      .select("machine, date, max(rate)")
      .date.gt(LocalDate.now().minusDays(10))
        .query().having().gt("max(rate)", 4)
      .findList();

    List<String> sql = LoggedSql.stop();
    assertThat(sql).hasSize(1);
    assertThat(sql.getFirst()).isNotNull();
    assertThat(result).isNotEmpty();
  }

  @Test
  public void groupBy_MachineWithJoin_dynamicFormula() {

    Query<DMachineStats> query = DB.find(DMachineStats.class)
      .select("date, max(rate), sum(totalKms)")
      .fetchQuery("machine", "name")
      .where().gt("date", LocalDate.now().minusDays(10))
      .query();

    List<DMachineStats> result = query.findList();
    // "select max(t0.rate), sum(t0.total_kms), t1.id, t1.name from d_machine_stats t0 left join dmachine t1 on t1.id = t0.machine_id  where t0.date > ?  group by t1.id, t1.name");
    assertThat(result).isNotEmpty();
  }

  @Test
  public void groupBy_MachineDateWithJoin_dynamicFormula() {

    Query<DMachineStats> query = DB.find(DMachineStats.class)
      .select("machine, date, max(rate), sum(totalKms)")
      .fetch("machine", "name")
      .where().gt("date", LocalDate.now().minusDays(10))
      .query();

    List<DMachineStats> result = query.findList();
    // "select t0.date, max(t0.rate), sum(t0.total_kms), t1.id, t1.name from d_machine_stats t0 left join dmachine t1 on t1.id = t0.machine_id  where t0.date > ?  group by t0.date, t1.id, t1.name");
    assertThat(result).isNotEmpty();

  }

  private static void loadData() {

    List<DMachine> machines = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      machines.add(new DMachine("Machine"+i));
    }

    DB.saveAll(machines);

    List<DMachineStats> allStats = new ArrayList<>();

    LocalDate date = LocalDate.now();
    for (int i = 0; i < 8; i++) {
      for (DMachine machine : machines) {

        DMachineStats stats = new DMachineStats(machine, date);

        stats.setHours(i * 4);
        stats.setTotalKms(i * 100);
        stats.setCost(BigDecimal.valueOf(i * 50));
        stats.setRate(BigDecimal.valueOf(i * 2));

        allStats.add(stats);
      }

      date = date.minusDays(1);
    }

    DB.saveAll(allStats);
  }
}
