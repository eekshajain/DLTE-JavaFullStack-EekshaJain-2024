package com.paymentdao.payment.remote;

import com.paymentdao.payment.entity.Payee;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PaymentTransferRepository {
    List<Payee> findAllPayeeBasedOnAccountNumber(Long accountNumber) ;
    List<Payee> findAllPayee();
}
