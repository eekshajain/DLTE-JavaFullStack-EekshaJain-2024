import com.google.gson.Gson;
import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
class Transactions{
    private String userName;
    private String password;
    private double withdrawAmount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
}
@WebServlet("/createTransaction/")
public class CreateNewTransactionPost extends HttpServlet {
    AccountService accountService;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService= new AccountService(storageTarget);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getReader().lines();
        Gson gson =new Gson();
        Transactions transactions=gson.fromJson(req.getReader(),Transactions.class);
        accountService.callWithdraw(transactions.getUserName(),transactions.getPassword(),transactions.getWithdrawAmount());
        resp.getWriter().println("Withdrawal of amount "+transactions.getWithdrawAmount()+" is completed!Transactions added into table");
        System.out.println("Withdrawal of amount "+transactions.getWithdrawAmount()+" is completed!Transactions added into table");
    }

}
