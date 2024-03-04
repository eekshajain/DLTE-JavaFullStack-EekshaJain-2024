package org.example;

public class TryProtected {
    private int varPrivate=12;
    protected int varProtected=13;
    int varDefault=14;
    public int varPublic=15;
//    private void varPublicReturn(){
//        System.out.println(varPrivate);
//    }
    protected void varProtectedReturn(){
        System.out.println(varProtected);
    }
   void varDefaultReturn(){
       System.out.println(varDefault);
    }
    public void varPublicReturn(){
        System.out.println(varPublic);
    }
}
