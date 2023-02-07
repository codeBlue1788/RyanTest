package org.example.tdd.tennis;

import java.util.HashMap;
import java.util.Map;

public class Tennis {

  private String firstPlayer;

  private String secondPlayer;

  private int firstPlayerPoint;

  private int secondPlayerPoint;

  public Tennis(String firstPlayer, String secondPlayer) {
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
  }

  public void addFirstPlayerPoint() {
    this.firstPlayerPoint++;
  }

  public void addSecondPlayerPoint() {
    this.secondPlayerPoint++;
  }

  public String score() {
    Map<Integer, String> lookUp = this.givenLookUp();

    if (isEven()) {
      if (isBothReachGamePoint()) {
        return "deuce";
      }
      return getResult(lookUp, "all");
    }

    if (isBothReachGamePoint()) {
      if (isDifferenceIs2()) {
        return getResult("win");
      }
      return getResult("adv");
    }

    if (isAnyPlayerReach4()) {
      return getResult("win");
    }
    return getResult(lookUp);
  }

  private boolean isBothReachGamePoint() {
    return isFirstPlayerReachGamePoint() && isSecondPlayerReachGamePoint();
  }

  private boolean isAnyPlayerReach4() {
    return this.firstPlayerPoint == 4 || this.secondPlayerPoint == 4;
  }

  private boolean isDifferenceIs2() {
    return Math.abs(this.firstPlayerPoint - this.secondPlayerPoint) == 2;
  }

  private String getAdvPlayer() {
    return this.firstPlayerPoint > this.secondPlayerPoint ? this.firstPlayer
        : this.secondPlayer;
  }

  private String getResult(String phrase) {
    return getAdvPlayer() + " " + phrase;
  }

  private String getResult(Map<Integer, String> lookUp, String phrase) {
    return lookUp.get(this.firstPlayerPoint) + " " + phrase;
  }

  private String getResult(Map<Integer, String> lookUp) {
    return lookUp.get(this.firstPlayerPoint) + " " + lookUp.get(this.secondPlayerPoint);
  }

  private boolean isFirstPlayerReachGamePoint() {
    return this.firstPlayerPoint >= 3;
  }

  private boolean isSecondPlayerReachGamePoint() {
    return this.secondPlayerPoint >= 3;
  }


  private Map<Integer, String> givenLookUp() {
    Map<Integer, String> lookUp = new HashMap<>();
    lookUp.put(0, "love");
    lookUp.put(1, "fifteen");
    lookUp.put(2, "thirty");
    lookUp.put(3, "forty");
    return lookUp;
  }

  private boolean isEven() {
    return this.firstPlayerPoint == this.secondPlayerPoint;
  }
}
