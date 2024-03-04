package org.example;

public interface TryDefaultInterface {
    int a=10;
    default void show(){
        System.out.println("Hello");
    }
  void show2();
}
