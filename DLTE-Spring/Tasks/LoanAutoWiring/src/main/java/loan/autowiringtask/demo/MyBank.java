package loan.autowiringtask.demo;

import loan.autowiringtask.demo.interfaces.LoanInterface;
import loan.autowiringtask.demo.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MyBank {
    @Autowired
    @Qualifier("homeImplementation")
    private LoanInterface loanInterface;
    public List<Loan> callFindAll(){
        List<Loan> fieldInjectedLoan=loanInterface.findAll();
        return fieldInjectedLoan;
    }
}
