package org.example.tdd.budget;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.example.tdd.budget.model.Budget;
import org.example.tdd.budget.repo.IBudgetRepo;

public class BudgetService {

  private IBudgetRepo budgetRepo;

  public BudgetService(IBudgetRepo budgetRepo) {
    this.budgetRepo = budgetRepo;
  }

  public int queryBudget(LocalDate startLocalDate, LocalDate endLocalDate) {
    if (startLocalDate.isAfter(endLocalDate)) {
      throw new IllegalArgumentException("時間格式錯誤");
    }
    List<Budget> budgets = budgetRepo.findAll();

    int total = 0;
    if (CollectionUtils.isNotEmpty(budgets)) {
      for (Budget budget : budgets) {
        YearMonth yearMonth = YearMonth.parse(budget.getYearMonth(),
            DateTimeFormatter.ofPattern("yyyyMM"));
        LocalDate firstDate = null;
        LocalDate endDate = null;
        if (yearMonth.atDay(1).isBefore(startLocalDate)) {
          firstDate = startLocalDate;
        }else{
          firstDate = yearMonth.atDay(1);
        }

        if (yearMonth.atEndOfMonth().isBefore(endLocalDate)) {
          endDate = yearMonth.atEndOfMonth();
        }else{
          endDate = endLocalDate;
        }
        if(firstDate.isAfter(endDate)){
          continue;
        }
        int between = (int) ChronoUnit.DAYS.between(firstDate, endDate) + 1;
        int perDay = budget.getAmount() / yearMonth.lengthOfMonth();
        total += between * perDay;
      }
    }
    return total;
  }
}
