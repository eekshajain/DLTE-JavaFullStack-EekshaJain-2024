package read;

public class MyEndPoint {
    private static String url="http://localhost:1011/read";
    public static void main(String[] args) {
      ReadService readService=new ReadService();
        System.out.println("Webservice running at "+url);

    }
}
