package handle.logs;

import java.util.ResourceBundle;

public class MyBankCreditCardException extends RuntimeException {
    public MyBankCreditCardException() {
        super(ResourceBundle.getBundle("application").getString("nodata.message"));
    }
}
