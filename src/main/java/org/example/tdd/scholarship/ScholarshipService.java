package org.example.tdd.scholarship;

import org.example.tdd.scholarship.model.Transcript;

public class ScholarshipService {


  public int calculate(Transcript transcript) {
    ICalculator calculator = findCalculator(transcript);
    return calculator.calculate(transcript);
  }

  private static ICalculator findCalculator(Transcript transcript) {
    return switch (transcript.getType()) {
      case Transcript.BACHELOR -> new BachelorCalculator();
      case Transcript.MASTER -> new MasterCalculator();
      case Transcript.PHD -> new PhDCalculator();
      default -> throw new IllegalArgumentException("Wrong Type!");
    };
  }
}
