package loan.autowiringtask.demo.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Configuration
public class Loan {
    private Long loanNumber;
    private Integer loanAmount;
    private Date loanDate;
    private String loanType;
    private String loanStatus;
    private String borrowerName;
    private Long borrowerContact;

    public Loan() {
        System.out.println("Object created");
    }

    public Loan(Long loanNumber, Integer loanAmount, Date loanDate, String loanType, String loanStatus, String borrowerName, Long borrowerContact) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanType = loanType;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getBorrowerContact() {
        return borrowerContact;
    }

    public void setBorrowerContact(Long borrowerContact) {
        this.borrowerContact = borrowerContact;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanDate=" + loanDate +
                ", loanType='" + loanType + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerContact=" + borrowerContact +
                '}';
    }

    public Long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
}
