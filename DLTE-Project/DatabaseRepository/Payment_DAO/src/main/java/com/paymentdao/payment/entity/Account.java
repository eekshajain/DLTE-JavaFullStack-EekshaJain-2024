package com.paymentdao.payment.entity;

import org.springframework.data.relational.core.sql.In;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class Account {
    @NotNull(message = "Must not be null")
    private Integer accountId;

    @NotNull(message = "Must not be null")
    private Integer customerId;

    @NotNull(message = "Must not be null")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "Not null")
    private String accountType;

    @NotNull(message = "Must not be null")
    @Digits(integer = 12, fraction = 0)
    private Long accountNumber;

    @NotNull(message = "Must not be null")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "Not null")
    private String accountStatus;

    @NotNull(message = "Must not be null")
    @Digits(integer = 8,fraction = 3)
    private Integer accountBalance;

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", customerId=" + customerId +
                ", accountType='" + accountType + '\'' +
                ", accountNumber=" + accountNumber +
                ", accountStatus='" + accountStatus + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }
}
