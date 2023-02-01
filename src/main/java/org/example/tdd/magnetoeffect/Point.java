package org.example.tdd.magnetoeffect;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Point {

  private int x;

  private int y;

  @Setter
  private double distance;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point point = (Point) o;
    return x == point.x && y == point.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  public double getDistance(Point mousePoint) {
    return Math.pow((mousePoint.getX() - getX()), 2) + Math.pow(
        (mousePoint.getY() - getY()), 2);
  }

  public boolean isAnchorToMousePointDistanceInRange(Point mousePoint) {
    return getDistance(mousePoint) <= Math.pow(5, 2);
  }
}
