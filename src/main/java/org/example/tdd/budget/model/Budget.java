package org.example.tdd.budget.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {

  private String yearMonth;

  private int amount;

  public Budget(String yearMonth, int amount) {
    this.yearMonth = yearMonth;
    this.amount = amount;
  }

  public LocalDate getEndOfMonth() {
    return this.getYearMonthObj().atEndOfMonth();
  }

  public LocalDate getFirstDay() {
    return this.getYearMonthObj()
        .atDay(1);
  }

  public boolean compareFirstDayAndStartDate(Period period) {
    return this.getFirstDay().isBefore(period.startLocalDate());
  }

  public YearMonth getYearMonthObj() {
    return YearMonth.parse(this.getYearMonth(),
        DateTimeFormatter.ofPattern("yyyyMM"));
  }

  public String getYearMonth() {
    return yearMonth;
  }

  public int getAmount() {
    return amount;
  }

  public boolean compareLastDayAndEndDate(Period period) {
    return this.getEndOfMonth().isBefore(period.endLocalDate());
  }

  public int getAvgAmount() {
    return getAmount() / getYearMonthObj().lengthOfMonth();
  }
}
