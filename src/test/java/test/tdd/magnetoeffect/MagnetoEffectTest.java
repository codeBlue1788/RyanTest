package test.tdd.magnetoeffect;

import org.example.tdd.magnetoeffect.MagnetoService;
import org.example.tdd.magnetoeffect.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MagnetoEffectTest {

  private MagnetoService magnetoService;

  @BeforeEach
  void setUp() {
    magnetoService = new MagnetoService();
  }

  @Test
  void test1_no_anchor() {

    Point newPoint = checkNewPoint(new Point(50, 50));

    Assertions.assertEquals(new Point(50, 50), newPoint);
  }

  @Test
  void test2_anchor_is_far() {
    // Arrange
    addAncor(new Point(56, 50));

    // Act
    Point newPoint = checkNewPoint(new Point(50, 50));

    // Assert
    Assertions.assertEquals(new Point(50, 50), newPoint);
  }

  @Test
  void test3_anchor_is_near() {
    // Arrange
    addAncor(new Point(48, 53));

    // Act
    Point newPoint = checkNewPoint(new Point(50, 50));

    // Assert
    Assertions.assertEquals(new Point(48, 53), newPoint);
  }


  @Test
  void test4_anchor_is_near_and_range_is_5() {
    // Arrange
    addAncor(new Point(55, 50));

    // Act
    Point newPoint = checkNewPoint(new Point(50, 50));

    // Assert
    Assertions.assertEquals(new Point(55, 50), newPoint);
  }

  @Test
  void test5_several_anchor_one_is_near() {
    // Arrange
    addAncor(new Point(48, 53));
    addAncor(new Point(56, 50));

    // Act
    Point newPoint = checkNewPoint(new Point(50, 50));

    // Assert
    Assertions.assertEquals(new Point(48, 53), newPoint);
  }

  @Test
  void test6_several_anchor_all_is_near() {
    // Arrange
    addAncor(new Point(51, 52));
    addAncor(new Point(51, 51));

    // Act
    Point newPoint = checkNewPoint(new Point(50, 50));

    // Assert
    Assertions.assertEquals(new Point(51, 51), newPoint);
  }

  @Test
  void test7_several_anchor_all_is_far() {
    // Arrange
    addAncor(new Point(56, 50));
    addAncor(new Point(56, 56));

    // Act
    Point newPoint = checkNewPoint(new Point(50, 50));

    // Assert
    Assertions.assertEquals(new Point(50, 50), newPoint);
  }

  @Test
  void test8_several_anchor_all_is_near_and_range_is_5() {
    // Arrange
    addAncor(new Point(54, 53));
    addAncor(new Point(55, 50));

    // Act
    Point newPoint = checkNewPoint(new Point(50, 50));

    // Assert
    Assertions.assertEquals(new Point(55, 50), newPoint);
  }

  @Test
  void test9_several_anchor_all_is_near_and_range_is_5() {
    // Arrange
    addAncor(new Point(54, 47));
    addAncor(new Point(54, 53));

    // Act
    Point newPoint = checkNewPoint(new Point(50, 50));

    // Assert
    Assertions.assertEquals(new Point(54, 53), newPoint);
  }

  @Test
  void test10_several_anchor_all_is_near_and_same() {
    // Arrange
    addAncor(new Point(54, 53));
    addAncor(new Point(54, 53));

    // Act
    Point newPoint = checkNewPoint(new Point(50, 50));

    // Assert
    Assertions.assertEquals(new Point(54, 53), newPoint);
  }

  private Point checkNewPoint(Point mousePoint) {
    return magnetoService.check(mousePoint);
  }

  private void addAncor(Point anchor) {
    magnetoService.addAnchor(anchor);
  }

}
