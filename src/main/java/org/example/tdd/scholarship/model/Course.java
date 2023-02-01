package org.example.tdd.scholarship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

  // 分數
  private double score;

  // 學分
  private int credit;

  public Course(double score) {
    this.score = score;
  }
}
