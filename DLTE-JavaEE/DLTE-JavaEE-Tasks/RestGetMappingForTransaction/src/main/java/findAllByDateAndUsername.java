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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

//http://localhost:7001/RestGetMappingForTransaction/transaction/findByDateAndUsername?date=2024-03-13&username=eeksha25
@WebServlet("transaction/findByDateAndUsername")
public class findAllByDateAndUsername extends HttpServlet {
    AccountService accountService;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService = new AccountService(storageTarget);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String date=req.getParameter("date");
        String username=req.getParameter("username");
        resp.setContentType("application/json");
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date parsedDate = (Date) dateFormat.parse(date);
        List<Transaction> transactions=accountService.callFindAllDate(Date.valueOf(date),username);
        Gson gson=new Gson();
        String allTransaction=gson.toJson(transactions);
        resp.getWriter().println(allTransaction);

    }


}
