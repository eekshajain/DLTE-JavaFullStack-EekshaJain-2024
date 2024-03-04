//import org.example;
import org.example.TryDefault;

public class outsidePackage extends TryDefault {
    public static void main(String[] args) {
        outsidePackage outSide=new outsidePackage();
        outSide.varPublicReturn();//without inheritance
        outSide.varProtectedReturn();
       // outSide.varDefaultReturn();
    }
}
