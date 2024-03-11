package sip;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/sip/*")
public class SIPCalculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double compoundInterest,maturityAmount;
       double amountInvested=Double.parseDouble(req.getParameter("amountInvested"));
       int numberOfPayments=Integer.parseInt(req.getParameter("numberOfPayments"));
       double rateOfInterest=Double.parseDouble(req.getParameter("rateOfInterest"));
        compoundInterest=rateOfInterest/(12*100);
        double periodicROI=Math.pow(1+compoundInterest,numberOfPayments);
        maturityAmount=amountInvested*((periodicROI-1)/compoundInterest)*(1+compoundInterest);
        resp.setContentType("application/json");
        Gson gson=new Gson();
        String message=gson.toJson(maturityAmount);
        resp.getWriter().println(message);
    }
}
