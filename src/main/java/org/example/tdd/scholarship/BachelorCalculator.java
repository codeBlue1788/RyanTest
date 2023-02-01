package org.example.tdd.scholarship;

import java.util.List;
import org.example.tdd.scholarship.model.Course;
import org.example.tdd.scholarship.model.Transcript;

public class BachelorCalculator implements ICalculator {

  @Override
  public int calculate(Transcript transcript) {
    List<Course> courses = transcript.getCourses();
    if (transcript.hasNoCourse(courses)) {
      // 沒修課
      return 0;
    }

    // 學士
    double overEightyPercent = transcript.calculateCoursePercent(courses);

    if (overEightyPercent >= 0.5) {
      return 10000;
    }

    if (overEightyPercent >= 0.3) {
      return 5000;
    }

    return 0;
  }

}