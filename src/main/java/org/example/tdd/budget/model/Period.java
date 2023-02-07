package org.example.tdd.budget.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Period(LocalDate startLocalDate, LocalDate endLocalDate) {

  public boolean validateDate() {
    return this.startLocalDate.isAfter(this.endLocalDate);
  }

  public int getOverLappingDays(Budget budget) {
    int days = (int) ChronoUnit.DAYS.between(
        budget.compareFirstDayAndStartDate(this) ? this.startLocalDate()
            : budget.getFirstDay(), budget.compareLastDayAndEndDate(this)
            ? budget.getEndOfMonth() : this.endLocalDate()) + 1;
    return Math.max(days, 0);
  }
}