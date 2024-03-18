package read;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.services.AccountService;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ReadService {
    TransactionGroup transactionGroup=new TransactionGroup();
AccountService accountService;

    public ReadService() {
        accountService=new AccountService(new DatabaseTarget());
    }

    @WebMethod
    @WebResult(name="ListOfUsers")
    public TransactionGroup findByDateAndUser(Date date, String username){
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
       transactionGroup.setTransactionList(accountService.callFindAllDate(sqlDate,username));
       return transactionGroup;
    }
}
