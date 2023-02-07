package org.example.tdd.scholarship.model;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transcript {

  public static final String BACHELOR = "BACHELOR";
  public static final String MASTER = "MASTER";
  public static final String PHD = "PHD";

  private List<Course> courses;

  private String type;

  public boolean isAllOverGivenScore(List<Course> courses, int x) {
    return courses.stream().allMatch(course -> course.getScore() >= x);
  }

  public double calculateAvgScore(List<Course> courses) {
    double scoreReduce = courses.stream().map(course -> course.getCredit() * course.getScore())
        .reduce(0D, Double::sum);
    double creditReduce = courses.stream().map(Course::getCredit)
        .reduce(0, Integer::sum);
    return scoreReduce / creditReduce;
  }


  public  double calculateCoursePercent(List<Course> courses) {
    double total = courses.size();
    double overEightyCourseCount = courses.
        stream()
        .filter(course -> course.getScore() >= 80)
        .count();
    return overEightyCourseCount / total;
  }

  public  boolean hasNoCourse(List<Course> courses) {
    return CollectionUtils.isEmpty(courses);
  }

  public boolean isMoreThanPercent(List<Course> courses, double percent) {
    return calculateCoursePercent(courses) >= percent;
  }

  public boolean isMoreThanAvgScore(List<Course> courses, int avgScore) {
    return calculateAvgScore(courses) >= avgScore;
  }
}
