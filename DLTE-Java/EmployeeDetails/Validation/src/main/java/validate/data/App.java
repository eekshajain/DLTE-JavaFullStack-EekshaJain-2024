package validate.data;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        ValidationOfData validationOfData=new ValidationOfData();
     //   System.out.println( "Hello World!" );
      if(validationOfData.isEmailValid("jaineeksha@gmail.com")){
          System.out.println("correct");
      }else{
          System.out.println("wrong");
      }
      if(validationOfData.isPhoneNumberValid(1234567890L)){
          System.out.println("true");
      }else System.out.println("false");

    }
}
