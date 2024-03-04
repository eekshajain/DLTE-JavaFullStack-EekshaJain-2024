package handle.logs;

        import com.sun.xml.internal.ws.api.WSDLLocator;

public class Exceptions {
    public static void main(String[] args) {
        try{
            int j=12/0;
        }finally{
            System.out.println("Error");
        }
    }
}
