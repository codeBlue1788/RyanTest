package test.patter.template;

import org.example.pattern.template.Coffee;
import org.example.pattern.template.Tea;
import org.junit.jupiter.api.Test;

class BeverageTest {

  @Test
  void test_tea() {
    var tea = new Tea(false);
    tea.prepareRecipe();
  }

  @Test
  void test_coffee() {
    var coffee = new Coffee(true);
    coffee.prepareRecipe();
  }

}
