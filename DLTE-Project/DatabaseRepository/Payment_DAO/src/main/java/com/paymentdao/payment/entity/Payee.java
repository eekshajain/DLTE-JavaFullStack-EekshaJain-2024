package com.paymentdao.payment.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Payee {

    @NotNull(message = "{payee.id}")
    @Digits(integer = 3, fraction = 0, message = "{payee.id}")
    private Integer payeeId;

    @NotNull(message = "{payee.senderAcc}")
    @Digits(integer = 12, fraction = 0, message = "{payee.senderAcc}")
    private Long senderAccountNumber;

    @NotNull(message = "{payee.payeeAcc}")
    @Digits(integer = 12, fraction = 0, message = "{payee.payeeAcc}")
    private Long payeeAccountNumber;

    @NotBlank(message = "{name.null}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{payee.holder}")
    private String payeeName;


    public Payee() {
    }

    public Payee(@NotNull(message = "{payee.id}") @Digits(integer = 3, fraction = 0, message = "{payee.id}") int payeeId, @NotNull(message = "{payee.senderAcc}") @Digits(integer = 12, fraction = 0, message = "{payee.senderAcc}") long senderAccountNumber, @NotNull(message = "{payee.payeeAcc}") @Digits(integer = 12, fraction = 0, message = "{payee.payeeAcc}") long payeeAccountNumber, @NotBlank(message = "{name.null}") @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{payee.holder}") String payeeName) {
        this.payeeId = payeeId;
        this.senderAccountNumber = senderAccountNumber;
        this.payeeAccountNumber = payeeAccountNumber;
        this.payeeName = payeeName;
    }

    public int getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(int payeeId) {
        this.payeeId = payeeId;
    }

    public long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(long senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public long getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public void setPayeeAccountNumber(long payeeAccountNumber) {
        this.payeeAccountNumber = payeeAccountNumber;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }
}
