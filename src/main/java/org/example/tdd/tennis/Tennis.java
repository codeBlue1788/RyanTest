package org.example.tdd.tennis;

public class Tennis {

  private String firstPlayer;

  private String secondPlayer;

  private int firstPlayerPoint;

  private int secondPlayerPoint;

  public Tennis(String firstPlayer, String secondPlayer) {
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
  }

  public void addFirstPlayerPoint(){
    this.firstPlayerPoint++;
  }

  public void addSecondPlayerPoint(){
    this.secondPlayerPoint++;
  }

  public String score(){
    return null;
  }
}
