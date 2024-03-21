package loan.autowiringtask.demo.implementations;

import loan.autowiringtask.demo.interfaces.LoanInterface;
import loan.autowiringtask.demo.model.Loan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("homeImplementation")
public class HomeLoanImplementation implements LoanInterface {
    List<Loan> homeLoanList=new ArrayList<>();
    @Override
    public List<Loan> findAll() {
        for(Loan loan:loanList){
            if(loan.getLoanType().equalsIgnoreCase("home"))
                homeLoanList.add(loan);
        }
        return homeLoanList;
    }
}
