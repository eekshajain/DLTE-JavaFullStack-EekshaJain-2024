package basics.service;

import java.util.Date;

public interface MyBank {
    static Loan[] loans = new Loan[10];
    void addLoan(int size);
    void availableLoan();
    void closedLoan();
}
