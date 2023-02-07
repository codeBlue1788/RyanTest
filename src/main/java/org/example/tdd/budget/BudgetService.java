package org.example.tdd.budget;

import java.util.List;
import org.example.tdd.budget.model.Budget;
import org.example.tdd.budget.model.Period;
import org.example.tdd.budget.repo.IBudgetRepo;

public class BudgetService {

  private IBudgetRepo budgetRepo;

  public BudgetService(IBudgetRepo budgetRepo) {
    this.budgetRepo = budgetRepo;
  }

  public int queryBudget(Period period) {
    if (period.validateDate()) {
      throw new IllegalArgumentException("時間格式錯誤");
    }
    List<Budget> budgets = budgetRepo.findAll();
    return budgets
        .stream()
        .map(budget -> period.getOverLappingDays(budget) * budget.getAvgAmount())
        .reduce(0, Integer::sum);
  }


}
