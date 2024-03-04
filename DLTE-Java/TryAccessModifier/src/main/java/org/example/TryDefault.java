package org.example;

 public class TryDefault {
    private int varPrivate=12;
    protected int varProtected=13;
    final int varDefault=14;
    public int varPublic=15;
 TryProtected tr1=new TryProtected();

    private void varPrivateReturn(){
        System.out.println(varPrivate);
    }
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

