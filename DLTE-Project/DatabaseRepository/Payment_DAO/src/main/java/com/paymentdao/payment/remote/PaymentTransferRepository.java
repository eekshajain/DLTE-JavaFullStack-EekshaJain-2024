package com.paymentdao.payment.remote;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.entity.Transaction;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PaymentTransferRepository {
    List<Payee> findAllPayeeBasedOnAccountNumber(Long accountNumber) ;
    List<Payee> findAllPayee();
   // void processTransaction(long senderAccountNumber,long payeeAccountNumber,String transactionType,double transactionAmount);
    Transaction processTransaction(Transaction transaction);
   // Transaction processTransaction(Long senderAcc,Transaction transaction);
}
