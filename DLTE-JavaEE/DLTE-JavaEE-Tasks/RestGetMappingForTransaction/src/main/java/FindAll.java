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
import java.io.PrintWriter;
import java.util.List;

//http://localhost:7001/RestGetMappingForTransaction/transaction/findAll
@WebServlet("/transaction/findAll")
public class FindAll extends HttpServlet {
  AccountService accountService;

    @Override
    public void init() throws ServletException {  //initialization to get access to database storage target
        StorageTarget storageTarget=new DatabaseTarget();
        accountService= new AccountService(storageTarget);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/json");   //want result in json format
        List<Transaction> transactionList=accountService.callFindAll();//find all users
//        Gson gson=new Gson();
//        String transaction=gson.toJson(transactionList);//convert list to json
//        resp.getWriter().println(transaction);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transactions</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transactions</h1>");
        for(Transaction transaction:transactionList){
            writer.println("<div>");
            writer.println("<p>Name: " + transaction.getUser() + "</p>");
            writer.println("<p>Date: " + transaction.getDate() + "</p>");
            writer.println("<p>Amount: " + transaction.getAmount() + "</p>");
            writer.println("<p>Balance: " + transaction.getBalance() + "</p>");
            writer.println("</div>");
            writer.println("<br>");
        }
        writer.println("</body>");
        writer.println("</html>");
    }
}
