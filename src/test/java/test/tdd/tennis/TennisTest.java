package test.tdd.tennis;

import org.example.tdd.tennis.Tennis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TennisTest {

  private Tennis tennis;

  @BeforeEach
  void setUp() {
    tennis = new Tennis("Tom", "Jerry");
  }

  @Test
  void love_all() {
    // Arrange
    // Act
    String score = tennis.score();
    // Assert
    Assertions.assertEquals("love all", score);
  }

  @Test
  void fifteen_all() {
    // Arrange
    addFirstPlayerPoint(1);
    addSecondPlayerPoint(1);
    // Act
    String score = tennis.score();
    // Assert
    Assertions.assertEquals("fifteen all", score);
  }

  @Test
  void thirty_all() {
    // Arrange
    addFirstPlayerPoint(2);
    addSecondPlayerPoint(2);
    // Act
    String score = tennis.score();
    // Assert
    Assertions.assertEquals("thirty all", score);
  }

  @Test
  void deuce() {
    // Arrange
    addFirstPlayerPoint(3);
    addSecondPlayerPoint(3);
    // Act
    String score = tennis.score();
    // Assert
    Assertions.assertEquals("deuce", score);
  }
  @Test
  void fifteen_love() {
    // Arrange
    addFirstPlayerPoint(1);
    addSecondPlayerPoint(0);
    // Act
    String score = tennis.score();
    // Assert
    Assertions.assertEquals("fifteen love", score);
  }

  @Test
  void Tom_adv() {
    // Arrange
    addFirstPlayerPoint(4);
    addSecondPlayerPoint(3);
    // Act
    String score = tennis.score();
    // Assert
    Assertions.assertEquals("Tom adv", score);
  }


  @Test
  void Tom_win() {
    // Arrange
    addFirstPlayerPoint(4);
    addSecondPlayerPoint(1);
    // Act
    String score = tennis.score();
    // Assert
    Assertions.assertEquals("Tom win", score);
  }

  @Test
  void Tom_win2() {
    // Arrange
    addFirstPlayerPoint(5);
    addSecondPlayerPoint(3);
    // Act
    String score = tennis.score();
    // Assert
    Assertions.assertEquals("Tom win", score);
  }


  private void addSecondPlayerPoint(int point) {
    for (int i = 0; i < point; i++) {
    tennis.addSecondPlayerPoint();
    }
  }

  private void addFirstPlayerPoint(int point) {
    for (int i = 0; i < point; i++) {
      tennis.addFirstPlayerPoint();
    }
  }

}
