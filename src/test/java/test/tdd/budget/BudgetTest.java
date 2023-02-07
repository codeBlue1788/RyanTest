package test.tdd.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.example.tdd.budget.BudgetService;
import org.example.tdd.budget.model.Budget;
import org.example.tdd.budget.model.Period;
import org.example.tdd.budget.repo.IBudgetRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BudgetTest {

  private BudgetService budgetService;
  private IBudgetRepo budgetRepo;

  @BeforeEach
  void setUp() {
    budgetRepo = Mockito.mock(IBudgetRepo.class);
    budgetService = new BudgetService(budgetRepo);
  }

  @Test
  void test_wrong_time() {
    // Arrange
    LocalDate startDate = LocalDate.of(2022, 7, 25);
    LocalDate endDate = LocalDate.of(2022, 7, 5);

    // Act
    IllegalArgumentException exception = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> budgetService.queryBudget(new Period(startDate, endDate))
    );

    // Assert
    assertEquals("時間格式錯誤", exception.getMessage());
  }

  @Test
  void test_no_budget() {
    // Arrange
    LocalDate startDate = LocalDate.of(2022, 9, 5);
    LocalDate endDate = LocalDate.of(2022, 9, 15);

    // Act
    int amount = budgetService.queryBudget(new Period(startDate, endDate));

    // Assert
    assertEquals(0, amount);
  }

  @Test
  void test_no_cross_month() {
    // Arrange
    LocalDate startDate = LocalDate.of(2022, 7, 5);
    LocalDate endDate = LocalDate.of(2022, 7, 6);

    givenBudgets(Arrays.asList(
        new Budget("202206", 3000),
        new Budget("202207", 3100),
        new Budget("202208", 6200)
    ));

    // Act
    int amount = budgetService.queryBudget(new Period(startDate, endDate));

    // Assert
    assertEquals(200, amount);
  }

  @Test
  void test_no_cross_month_threshold() {
    // Arrange
    LocalDate startDate = LocalDate.of(2022, 7, 1);
    LocalDate endDate = LocalDate.of(2022, 7, 31);

    givenBudgets(Arrays.asList(
        new Budget("202206", 3000),
        new Budget("202207", 3100),
        new Budget("202208", 6200)
    ));

    // Act
    int amount = budgetService.queryBudget(new Period(startDate, endDate));

    // Assert
    assertEquals(3100, amount);
  }

  @Test
  void cross_month() {
    // Arrange
    LocalDate startDate = LocalDate.of(2022, 6, 15);
    LocalDate endDate = LocalDate.of(2022, 8, 10);

    givenBudgets(Arrays.asList(
        new Budget("202206", 3000),
        new Budget("202207", 3100),
        new Budget("202208", 6200)
    ));

    // Act
    int amount = budgetService.queryBudget(new Period(startDate, endDate));

    // Assert
    assertEquals(6700, amount);
  }

  @Test
  void cross_month_empty() {
    // Arrange
    LocalDate startDate = LocalDate.of(2022, 6, 15);
    LocalDate endDate = LocalDate.of(2022, 8, 10);

    givenBudgets(Arrays.asList(
        new Budget("202206", 3000),
        new Budget("202208", 6200)
    ));

    // Act
    int amount = budgetService.queryBudget(new Period(startDate, endDate));

    // Assert
    assertEquals(3600, amount);
  }

  @Test
  void cross_month_threshold() {
    // Arrange
    LocalDate startDate = LocalDate.of(2022, 7, 31);
    LocalDate endDate = LocalDate.of(2022, 8, 1);

    givenBudgets(Arrays.asList(
        new Budget("202206", 3000),
        new Budget("202207", 3100),
        new Budget("202208", 6200)
    ));

    // Act
    int amount = budgetService.queryBudget(new Period(startDate, endDate));

    // Assert
    assertEquals(300, amount);
  }

  private void givenBudgets(List<Budget> budgets) {
    Mockito.when(budgetRepo.findAll()).thenReturn(budgets);
  }
}
