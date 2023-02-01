package org.example.tdd.magnetoeffect;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

public class MagnetoService {

  private final List<Point> anchors = new ArrayList<>();

  // Before Refactor 45行
  // After Refactor 14行
  public Point check(Point mousePoint) {
    if (anchorsExist()) {
      List<Point> anchorsInRange = findAnchorsInRange(mousePoint);

      List<Point> anchorInRangeAndSorted = setAnchorToMousePointDistanceAndSorted(mousePoint,
          anchorsInRange);

      if (existSameNearestDistance(anchorInRangeAndSorted)) {
        return findNearestAnchorCompareByXThenYInSameDistance(mousePoint, anchorInRangeAndSorted);
      }
      return findNearestAnchor(mousePoint, anchorsInRange);
    }
    return mousePoint;
  }

  private List<Point> findAnchorsInRange(Point mousePoint) {
    return this.anchors.stream()
        .filter(anchor -> anchor.isAnchorToMousePointDistanceInRange(mousePoint)).toList();
  }

  private Point findNearestAnchorCompareByXThenYInSameDistance(Point mousePoint,
      List<Point> anchorInRangeAndSorted) {
    return anchorInRangeAndSorted.stream()
        .filter(anchor -> isNearestSameDistance(anchorInRangeAndSorted, anchor))
        .max(Comparator.comparing(Point::getX).thenComparing(Point::getY)).orElse(mousePoint);
  }

  private boolean isNearestSameDistance(List<Point> anchorInRangeAndSorted, Point anchor) {
    return anchor.getDistance() == anchorInRangeAndSorted.get(0).getDistance();
  }

  private Point findNearestAnchor(Point mousePoint, List<Point> anchorInRange) {
    return anchorInRange.stream()
        .min(Comparator.comparing(
            anchor -> anchor.getDistance(mousePoint)))
        .orElse(mousePoint);
  }

  private List<Point> setAnchorToMousePointDistanceAndSorted(Point mousePoint,
      List<Point> anchorInRange) {
    anchorInRange
        .forEach(anchor -> anchor.setDistance(anchor.getDistance(mousePoint)));
    return anchorInRange.stream()
        .sorted(Comparator.comparing(Point::getDistance))
        .toList();
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
