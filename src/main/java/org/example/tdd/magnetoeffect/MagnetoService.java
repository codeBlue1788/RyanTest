package org.example.tdd.magnetoeffect;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

public class MagnetoService {

  private final List<Point> anchors = new ArrayList<>();

  // Before Refactor 45行
  // After Refactor 8行
  public Point check(Point mousePoint) {
    if (anchorsExist()) {
      List<Point> anchorsInRange = setAnchorToMousePointDistance(mousePoint);
      return findNearestAnchor(mousePoint, anchorsInRange,
          existSameNearestDistance(anchorsInRange));
    }
    return mousePoint;
  }

  private List<Point> setAnchorToMousePointDistance(Point mousePoint) {
    List<Point> anchorInRange = this.anchors.stream()
        .filter(anchor -> anchor.isAnchorToMousePointDistanceInRange(mousePoint)).toList();
    anchorInRange
        .forEach(anchor -> anchor.setDistance(anchor.calculateDistance(mousePoint)));
    return anchorInRange.stream()
        .sorted(Comparator.comparing(Point::getDistance))
        .toList();
  }

  private Point findNearestAnchor(Point mousePoint, List<Point> anchorInRangeAndSorted,
      boolean isSameNearestDistance) {
    if (isSameNearestDistance) {
      return anchorInRangeAndSorted.stream()
          .filter(anchor -> isSameNearestDistance(anchorInRangeAndSorted, anchor))
          .max(Comparator.comparing(Point::getX).thenComparing(Point::getY)).orElse(mousePoint);
    }
    return anchorInRangeAndSorted.stream()
        .min(Comparator.comparing(Point::getDistance))
        .orElse(mousePoint);
  }

  private boolean isSameNearestDistance(List<Point> anchorInRangeAndSorted, Point anchor) {
    return anchor.getDistance() == anchorInRangeAndSorted.get(0).getDistance();
  }


  private boolean existSameNearestDistance(List<Point> anchorInRangeAndSorted) {
    return anchorInRangeAndSorted.size() > 1
        && anchorInRangeAndSorted.get(0).getDistance() == anchorInRangeAndSorted.get(1)
        .getDistance();
  }

  private boolean anchorsExist() {
    return CollectionUtils.isNotEmpty(this.anchors);
  }

  public void addAnchor(Point anchor) {
    this.anchors.add(anchor);
  }
}
