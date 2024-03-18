package validate.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ValidationOfData {

    public static boolean isEmailValid(String emailID){
        String regex = "^[a-zA-Z0-9_+*-]+(?:\\.[a-zA-Z0-9_+*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailID);
        return matcher.matches();
    }

    public static boolean isPinCodeValid(int pinCode){
        String pin=String.valueOf(pinCode);
        String regex= "^[1-9][0-9]{5}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pin);
        return matcher.matches();
    }

    public static boolean isPhoneNumberValid(long phoneNumber){
        String phone=String.valueOf(phoneNumber);
        String regex= "^[6-9][0-9]{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
