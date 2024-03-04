package generics.example.collections;

import java.io.IOException;

public class ThrowsException {
    public static void main(String[] args) {
        try{
            Test exc=new Test();
            exc.method();
        }catch (Exception e) {System.out.println("");}
    }
}

class Test{
    void method(){
        throw new IOException("error");
    }
}
