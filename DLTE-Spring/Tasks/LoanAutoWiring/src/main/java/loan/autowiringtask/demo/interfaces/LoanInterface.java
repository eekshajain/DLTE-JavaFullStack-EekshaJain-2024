package loan.autowiringtask.demo.interfaces;

import loan.autowiringtask.demo.model.Loan;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface LoanInterface {
    List<Loan> loanList= Stream.of(new Loan(121344L,50000,new Date("2/3/2024"),"personal","open","Divija",9865432108L),new Loan(121344L,50000,new Date("2/3/2024"),"home","open","Ankitha",9865432108L),new Loan(121344L,50000,new Date("2/3/2024"),"personal","open","Arundhathi",9865432108L),new Loan(121344L,50000,new Date("2/3/2024"),"home","open","Akshira",9865432108L),new Loan(121344L,50000,new Date("2/3/2024"),"personal","open","Annapoorna",9865432108L)).collect(Collectors.toList());
   List<Loan> findAll();
}
