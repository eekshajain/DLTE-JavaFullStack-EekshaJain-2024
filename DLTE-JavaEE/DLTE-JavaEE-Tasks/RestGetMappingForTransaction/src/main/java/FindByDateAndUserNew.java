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
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("transaction/findByDateAndUsernameNew")
public class FindByDateAndUserNew extends HttpServlet {
    FindAllByDateAndUsernameSOAP findAllByDateAndUsernameSOAP=new FindAllByDateAndUsernameSOAP();
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
        List<Transaction> transactions= null;
        try {
            transactions = findAllByDateAndUsernameSOAP.findByDateAndUser(Date.valueOf(date),username);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        //List<Transaction> transactions=accountService.callFindAllDate(Date.valueOf(date),username);
        Gson gson=new Gson();
        String allTransaction=gson.toJson(transactions);
        resp.getWriter().println(allTransaction);
//        Map<String,String> params=new HashMap<>();
//        params.put("date",date);
//        params.put("username",username);
//        Gson gson=new Gson();
//        String jsonParams=gson.toJson(params);
//        try {
//            List<Transaction> transactions=findAllByDateAndUsernameSOAP.findByDateAndUser(jsonParams);
//            String allTransaction = gson.toJson(transactions);
//            resp.getWriter().println(allTransaction);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }


    }

}
