package org.example;

public class withinPackge  {
    public static void main(String[] args) {
        //TryProtected try1=new TryProtected();
        //TryPrivate try2=new TryPrivate();
    final  TryDefault try3 ;
    try3=new TryDefault();
        try3.varPublic=12;
//        System.out.println(try1.varDefault);
//        System.out.println(try1.varProtected);
//       // System.out.println(try1.varPrivate);
//        System.out.println(try1.varPublic);
//        try1.varDefaultReturn();
//        try1.varProtectedReturn();
//        try1.varPublicReturn();
//        //try1.varPrivateReturn();
//        try2.varDefaultReturn();
        try3.varPublicReturn();
        try3.varDefaultReturn();
        try3.varProtectedReturn();
    }
}
