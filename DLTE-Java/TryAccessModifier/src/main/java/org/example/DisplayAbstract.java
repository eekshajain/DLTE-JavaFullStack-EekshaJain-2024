package org.example;

public class DisplayAbstract extends TryAbstract {
    public static void main(String[] args) {
        DisplayAbstract ab=new DisplayAbstract();
        ab.display();
        ab.display2();
    }
    @Override
    public void display() {
        System.out.println("Abstract method");
    }

    @Override
    public void display2() {
        super.display2();
    }
}
