package transaction.activities.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import transaction.activities.demo.model.Transaction;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction,Long>{
    //specify @Param("name") here need not specify in @Pathvariable
    @Query(value="select * from transaction_table where username=:name and transaction_type=:type",nativeQuery = true)
    Transaction findDetails(String name,String type);
    @Query("from Transaction where amount between :amount1 and :amount2")
    List<Transaction> findDetailsByAmount(double amount1, double amount2);
}
