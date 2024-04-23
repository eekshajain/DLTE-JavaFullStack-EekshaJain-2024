import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@SessionScoped
public class LoanProduct {

    List<Loan> loans;

    public LoanProduct() {
        loans=new ArrayList<>();
     loans.add(new Loan(123456L,25550,"3/2/2024","open","Eeksha",98762541425L));
       loans.add(new Loan(123457L,35000,"3/3/2024","closed","Spandana",97362637382L));


    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void addLoan(Loan loan){
        loans.add(loan);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Loans added successfully",null));
    }

    public List<Loan> displayClosedLoan(){
     return loans.stream().filter(loan1->loan1.getLoanStatus().equals("closed")).collect(Collectors.toList());
    }

    public void deleteLoan(Long loanNumber){
        loans.removeIf(loan -> loan.getLoanNumber()==loanNumber);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Loans deleted",null));
    }
}
