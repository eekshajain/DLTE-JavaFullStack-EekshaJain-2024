package transaction.activities.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transaction.activities.demo.model.Transaction;
import transaction.activities.demo.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionServices {
@Autowired
    TransactionRepository transactionRepository;

    public Transaction newTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
    public List<Transaction> displayAll(){
        return transactionRepository.findAll();
    }
    public Transaction findDetailsByUserAndType(String name,String type){
        return transactionRepository.findDetails(name,type);
    }

    public List<Transaction> findDetailsByAmountOfTransaction(double amount1,double amount2){
        return transactionRepository.findDetailsByAmount(amount1,amount2);
    }
}
