package org.example.pattern.template;

public class Tea extends Beverage{

  @Override
  void addCondiments() {
    System.out.println("幫茶加一些檸檬...");
  }

  @Override
  void brew() {
    System.out.println("用茶包泡茶...");
  }

  public Tea(boolean wantToAddCondiments) {
    super(wantToAddCondiments);
  }
}
