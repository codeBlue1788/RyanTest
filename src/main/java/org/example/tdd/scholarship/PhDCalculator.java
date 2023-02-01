package org.example.tdd.scholarship;

import java.util.List;
import org.example.tdd.scholarship.model.Course;
import org.example.tdd.scholarship.model.Transcript;

public class PhDCalculator implements ICalculator {

  @Override
  public int calculate(Transcript transcript) {
    List<Course> courses = transcript.getCourses();
    if (transcript.hasNoCourse(courses)) {
      // 沒修課
      return 0;
    }

    // 博士
    boolean isAllOverNinety = transcript.isAllOverGivenScore(courses, 90);
    boolean isAllOverEighty = transcript.isAllOverGivenScore(courses, 80);
    if (isAllOverNinety) {
      return 40000;
    } else if (isAllOverEighty) {
      return 20000;
    }

    return 0;
  }
}