package handle.logs;

public class Account {
    protected Long accountNumber;
    protected Integer accountBalance;
    protected String accountName;
    public  Account(){

    }

    public Account(Long accountNumber, Integer accountBalance, String accountName) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountName = accountName;
    }


}

