package org.example.tdd.scholarship.model;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transcript {
  private List<Course> courses;

  private String degreeType;
}
