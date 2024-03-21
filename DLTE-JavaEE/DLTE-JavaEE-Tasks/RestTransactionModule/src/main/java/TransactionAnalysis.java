import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@WebServlet("/transaction/*")
public class TransactionAnalysis extends HttpServlet {
    List<Transaction> myTransaction;

    @Override
    public void init() throws ServletException {
                myTransaction= Stream.of(new Transaction(new Date("3/2/2024"),2300,"Vinitha","Friend"),
                new Transaction(new Date("4/2/2024"),200,"Vani","Bills"),
                new Transaction(new Date("5/2/2024"),1500,"Health Clinic","Emergency"),
                new Transaction(new Date("6/2/2024"),1000,"Babita","Family")).collect(Collectors.toList());
            System.out.println("initiated");

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();
        Transaction transaction=gson.fromJson(req.getReader(),Transaction.class);
        myTransaction.add(transaction);
        resp.getWriter().println(transaction.getDateOfTransaction()+" "+transaction.getSentTo()+" is added");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String minimumAmount=req.getParameter("minAmount");
        String maximumAmount=req.getParameter("maxAmount");
        if(minimumAmount!=null & maximumAmount!=null){
            int max=Integer.parseInt(maximumAmount);
            int min=Integer.parseInt(minimumAmount);
            Gson gson=new Gson();
            resp.setContentType("application/json");
            for (Transaction each:myTransaction){
                if(each.getAmountOfTransaction()>=min && each.getAmountOfTransaction()<max){
                   resp.getWriter().println(each);
                }
            }
        }else{
            Gson gson=new Gson();
            resp.setContentType("application/json");
            String json = gson.toJson(myTransaction);
            resp.getWriter().println(json);
        }
    }

    @Override
    public void destroy() {

    }
}