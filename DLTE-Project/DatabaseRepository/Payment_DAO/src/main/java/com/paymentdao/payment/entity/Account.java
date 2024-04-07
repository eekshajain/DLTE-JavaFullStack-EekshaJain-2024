package com.paymentdao.payment.entity;

import org.springframework.data.relational.core.sql.In;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class Account {
    @NotNull(message = "{accountId.notnull}")
    private Integer accountId;

    @NotNull(message = "{customerId.notnull}")
    private Integer customerId;

    @NotBlank(message = "{accountType.notBlank}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{pattern.accountType.message}")
    private String accountType;

    @NotNull(message = "{accountNumber.notnull}")
    @Digits(integer = 12, fraction = 0, message = "{digits.accountNumber.message}")
    private Long accountNumber;

    @NotNull(message = "{accountStatus.notNull}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{pattern.accountStatus.message}")
    private String accountStatus;

    @NotNull(message = "{accountBalance.notNull}")
    @Digits(integer = 8, fraction = 3, message = "{digits.accountBalance.message}")
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
