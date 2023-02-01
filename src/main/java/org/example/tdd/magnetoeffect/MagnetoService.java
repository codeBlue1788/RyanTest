package org.example.tdd.magnetoeffect;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections.CollectionUtils;

public class MagnetoService {

  private List<Point> anchors = new ArrayList<>();

  // Before Refactor 45行
  public Point check(Point mousePoint) {
    if (CollectionUtils.isEmpty(this.anchors)) {
      return mousePoint;
    }

    // 過濾出在範圍內的錨點
    List<Point> anchorInRangeAndSorted = this.anchors.stream()
        .filter(anchor -> Math.pow((mousePoint.getX() - anchor.getX()), 2) + Math.pow(
            (mousePoint.getY() - anchor.getY()), 2) <= Math.pow(5, 2))
        .map(anchor -> {
          double distance = Math.pow((mousePoint.getX() - anchor.getX()), 2) + Math.pow(
              (mousePoint.getY() - anchor.getY()), 2);
          anchor.setDistance(distance);
          return anchor;
        })
        .sorted(Comparator.comparing(Point::getDistance)).toList();
    // 判斷最小距離是否至少有兩個相同
    if (anchorInRangeAndSorted.size() > 1
        && anchorInRangeAndSorted.get(0).getDistance() == anchorInRangeAndSorted.get(1)
        .getDistance()) {
      // 過濾出相同距離的錨點
      List<Point> sameDistanceAnchor = anchorInRangeAndSorted.stream()
          .filter(anchor -> anchor.getDistance() == anchorInRangeAndSorted.get(0).getDistance())
          .toList();
      // 相同距離先比X軸，再比Y軸
      Optional<Point> first = sameDistanceAnchor.stream()
          .sorted(Comparator.comparing(Point::getX).thenComparing(Point::getY).reversed())
          .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    } else {
      Optional<Point> first = this.anchors.stream()
          .filter(anchor -> Math.pow((mousePoint.getX() - anchor.getX()), 2) + Math.pow(
              (mousePoint.getY() - anchor.getY()), 2) <= Math.pow(5, 2))
          .sorted(Comparator.comparing(
              anchor -> Math.pow((mousePoint.getX() - anchor.getX()), 2) + Math.pow(
                  (mousePoint.getY() - anchor.getY()), 2)))
          .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    return mousePoint;
  }

  public void addAnchor(Point anchor) {
    this.anchors.add(anchor);
  }
}
