package loan.examples.demo1;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//     http://localhost:8083/loan/
@RestController
@RequestMapping("/loan")
public class LoanRestController {
    List<Loan> loans =new ArrayList<>();

    public LoanRestController() {
        loans.add(new Loan(12345L,500000,new Date("3/2/2024"),"open"));
        loans.add(new Loan(12346L,1500000,new Date("1/12/2024"),"closed"));
        loans.add(new Loan(12347L,2000000,new Date("2/12/2024"),"closed"));
        loans.add(new Loan(12348L,1000000,new Date("11/2/2024"),"open"));
    }

    @GetMapping("/{employeeIndex}")
    public Loan readOne(@PathVariable("employeeIndex") int index){

        return loans.get(index);
    }


    @PostMapping("/")
    public String addNewLoan(@RequestBody List<Loan> loan){
        for(Loan loan1:loan){
            loans.add(loan1);
        }
        return "New Loan object "+loan+ " added";
    }
}

