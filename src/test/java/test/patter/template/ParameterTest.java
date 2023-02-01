package test.patter.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterTest {
  @ParameterizedTest
  @ValueSource(ints = {2, 4, 6})
  void checkEvenNumber(int num) {
    assertEquals(0, num % 2, "給定參數為質數");
  }
}
