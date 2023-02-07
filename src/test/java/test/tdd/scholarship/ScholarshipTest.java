package test.tdd.scholarship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.tdd.scholarship.ScholarshipService;
import org.example.tdd.scholarship.model.Course;
import org.example.tdd.scholarship.model.Transcript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScholarshipTest {

  private ScholarshipService service;

  @BeforeEach
  void setUp() {
    service = new ScholarshipService();
  }

  @Test
  void test_no_scholarship() {
    // Arrange
    Transcript transcript = givenTranscript(new ArrayList<>(), Transcript.BACHELOR);
    // Act
    int amount = calculate(transcript);

    // Assert
    Assertions.assertEquals(0, amount);
  }

  private static Transcript givenTranscript(List<Course> courses, String type) {
    return new Transcript(courses, type);
  }

  @Test
  void test_get_full_scholarship() {
    // Arrange
    Transcript transcript = givenTranscript(Arrays.asList(
        new Course(80),
        new Course(80),
        new Course(60),
        new Course(70)
    ), Transcript.BACHELOR);
    // Act
    int amount = calculate(transcript);

    // Assert
    Assertions.assertEquals(10000, amount);
  }

  private int calculate(Transcript transcript) {
    return service.calculate(transcript);
  }

  @Test
  void test_get_half_scholarship() {
    // Arrange
    Transcript transcript = givenTranscript(Arrays.asList(
        new Course(80),
        new Course(80),
        new Course(70),
        new Course(70),
        new Course(60),
        new Course(70)
    ), Transcript.BACHELOR);
    // Act
    int amount = calculate(transcript);

    // Assert
    Assertions.assertEquals(5000, amount);
  }

  @Test
  void test_master_get_full_scholarship() {
    // Arrange
    Transcript transcript = givenTranscript(Arrays.asList(
        new Course(85, 2),
        new Course(100, 1)
    ), Transcript.MASTER);
    // Act
    int amount = calculate(transcript);

    // Assert
    Assertions.assertEquals(15000, amount);
  }

  @Test
  void test_master_get_half_scholarship() {
    // Arrange
    Transcript transcript = givenTranscript(Arrays.asList(
        new Course(70, 2),
        new Course(100, 1)
    ), Transcript.MASTER);
    // Act
    int amount = calculate(transcript);

    // Assert
    Assertions.assertEquals(7500, amount);
  }

  @Test
  void test_phd_get_full_scholarship() {
    // Arrange
    Transcript transcript = givenTranscript(Arrays.asList(
        new Course(92),
        new Course(90),
        new Course(91)
    ), Transcript.PHD);
    // Act
    int amount = calculate(transcript);

    // Assert
    Assertions.assertEquals(40000, amount);
  }

  @Test
  void test_phd_get_half_scholarship() {
    // Arrange
    Transcript transcript = givenTranscript(Arrays.asList(
        new Course(80),
        new Course(82),
        new Course(91)
    ), Transcript.PHD);
    // Act
    int amount = calculate(transcript);

    // Assert
    Assertions.assertEquals(20000, amount);
  }

  @Test
  void test_wrong_type() {
    // Arrange
    Transcript transcript = givenTranscript(Arrays.asList(
        new Course(80),
        new Course(82),
        new Course(91)
    ), "NONE");

    // Act
    IllegalArgumentException exception = Assertions.assertThrows(
        IllegalArgumentException.class, () -> calculate(transcript));

    // Assert
    Assertions.assertEquals("Wrong Type!", exception.getMessage());
  }

}
