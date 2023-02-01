package org.example.pattern.template;

public abstract class Beverage {

  private final boolean wantToAddCondiments;

   Beverage(boolean wantToAddCondiments) {
    this.wantToAddCondiments = wantToAddCondiments;
  }

  public final void prepareRecipe() {

    // 煮開水
    boilWater();

    // 用沸水沖泡
    brew();

    // 把飲料倒進杯子
    pourInCup();

    if (wantToAddCondiments) {
      // 加配料
      addCondiments();
    }
  }

  // 子類實作
  abstract void addCondiments();

  // 子類實作
  abstract void brew();

  // 共用
  private void pourInCup() {
    System.out.println("煮好倒進杯子裡...");
  }

  private void boilWater() {
    System.out.println("開始煮開水了...");
  }

}
