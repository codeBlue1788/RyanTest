package org.example.tdd.scholarship;

import java.util.List;
import org.example.tdd.scholarship.model.Course;
import org.example.tdd.scholarship.model.Transcript;

public class MasterCalculator implements ICalculator {

  @Override
  public int calculate(Transcript transcript) {
    List<Course> courses = transcript.getCourses();
    if (transcript.hasNoCourse(courses)) {
      // 沒修課
      return 0;
    }

    // 碩士
    double avgScore = transcript.calculateAvgScore(courses);

    if (avgScore >= 90) {
      return 15000;
    }

    if (avgScore >= 80) {
      return 7500;
    }

    return 0;
  }

}