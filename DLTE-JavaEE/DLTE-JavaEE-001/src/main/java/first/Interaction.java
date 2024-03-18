package first;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/second/*")
public class Interaction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      int principal_amount = Integer.parseInt(req.getParameter("principle"));
        int tenure=Integer.parseInt(req.getParameter("tenure"));
        double totalRepay=0.0 ,EMI=0.0;
        if(principal_amount>=100000 && principal_amount<300000)
            totalRepay=principal_amount+(principal_amount*0.24);
        else{
            totalRepay=principal_amount+(principal_amount*0.29);
        }
        EMI=totalRepay/tenure;
        resp.setContentType("application/json");
        Gson gson=new Gson();
        String message=gson.toJson(EMI);
        resp.getWriter().println(message);
    }
}
