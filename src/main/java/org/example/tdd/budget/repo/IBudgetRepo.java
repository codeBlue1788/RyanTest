package org.example.tdd.budget.repo;

import java.util.List;
import org.example.tdd.budget.model.Budget;

public interface IBudgetRepo {

  List<Budget> findAll();
}
