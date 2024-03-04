import org.example.TryDefaultInterface;

public class TryOutside implements TryDefaultInterface {
    public static void main(String[] args) {
        TryOutside tryOut=new TryOutside();
        tryOut.show();
        System.out.println(TryDefaultInterface.a);
    }

    @Override
    public void show2() {

    }
}
