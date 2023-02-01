package org.example.pattern.template;

public class Coffee extends Beverage{

  @Override
  void addCondiments() {
    System.out.println("加點牛奶做拿鐵...");
  }

  @Override
  void brew() {
    System.out.println("用咖啡豆磨粉泡咖啡");
  }

  public Coffee(boolean wantToAddCondiments) {
    super(wantToAddCondiments);
  }
}
