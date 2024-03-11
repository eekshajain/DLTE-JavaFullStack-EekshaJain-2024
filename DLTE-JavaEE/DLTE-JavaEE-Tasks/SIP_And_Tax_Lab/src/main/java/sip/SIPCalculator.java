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
       String amount=req.getParameter("amountInvested");
       String paymentNumber=req.getParameter("numberOfPayments");
       String interestRate=req.getParameter("rateOfInterest");
        String regime=req.getParameter("regime");
        String sal=req.getParameter("salary");
       if(amount!=null && paymentNumber!=null && interestRate!=null ) {
           double amountInvested=Double.parseDouble(amount);
           int numberOfPayments=Integer.parseInt(paymentNumber);
           double rateOfInterest=Double.parseDouble(interestRate);
           compoundInterest = rateOfInterest / (12 * 100);
           double periodicROI = Math.pow(1 + compoundInterest, numberOfPayments);
           maturityAmount = amountInvested * ((periodicROI - 1) / compoundInterest) * (1 + compoundInterest);
           resp.setContentType("application/json");
           Gson gson = new Gson();
           String message = gson.toJson(maturityAmount);
           resp.getWriter().println(message);
       }else{
           Gson gson = new Gson();
           double salary=Integer.parseInt(sal);
           String received=taxSlab(regime,salary);
           String message=gson.toJson(received);
           resp.getWriter().println(message);
       }
    }

   public String taxSlab(String regime,Double salary)
   {

       double taxOldRegime=0,taxNewRegime=0;
       String message="";
       switch (regime.toLowerCase()){
           case "old":
               System.out.println("Enter your salary: ");
               if(salary>0&& salary<250000){
                   message="Exempt in both the regimes";
               }else if(salary>=250000 && salary<500000){
                   taxOldRegime=salary*0.05;
                   message="5% of salary to be paid under old regime for range 2.5 lakh to 5 lakh.Amount to be paid is "+taxOldRegime;
               }else if(salary>=500000 && salary<750000){
                   taxOldRegime=salary*0.2;
                   message="20% of salary to be paid under old regime for range 5 lakh to 7.5 lakh.Amount to be paid is "+taxOldRegime;
               }else if(salary>=750000 && salary<1000000){
                   taxOldRegime=salary*0.2;
                   message="20% of salary to be paid under old regime for range 7.5 lakh to 10 lakh.Amount to be paid is "+taxOldRegime;
               }else if(salary>=1000000 && salary<1250000){
                   taxOldRegime=salary*0.3;
                   message="30% of salary to be paid under old regime for range 10 lakh to 12.5 lakh.Amount to be paid is "+taxOldRegime;
               }else if(salary>=1250000 && salary<1500000){
                   taxOldRegime=salary*0.3;
                   message="30% of salary to be paid under old regime for range 12.5 lakh to 15 lakh.Amount to be paid is "+taxOldRegime;
               }else{
                   taxOldRegime=salary*0.3;
                   message="30% of salary to be paid under old regime for above 15 lakh.Amount to be paid is "+taxOldRegime;
               }
               break;
           case "new" :
               System.out.println("Enter your salary: ");
               if(salary>0&& salary<250000){
                   message="Exempt in both the regimes";
               }else if(salary>=250000 && salary<500000){
                   taxNewRegime=salary*0.05;
                   message="5% of salary to be paid under new regime for range 2.5 lakh to 5 lakh.Amount to be paid is "+taxNewRegime;
               }else if(salary>=500000 && salary<750000){
                   taxNewRegime=salary*0.1;
                   message="10% of salary to be paid under new regime for range 5 lakh to 7.5 lakh.Amount to be paid is "+taxNewRegime;
               }else if(salary>=750000 && salary<1000000){
                   taxNewRegime=salary*0.15;
                   message="15% of salary to be paid under new regime for range 7.5 lakh to 10 lakh.Amount to be paid is "+taxNewRegime;
               }else if(salary>=1000000 && salary<1250000){
                   taxNewRegime=salary*0.2;
                   message="20% of salary to be paid under new regime for range 10 lakh to 12.5 lakh.Amount to be paid is "+taxNewRegime;
               }else if(salary>=1250000 && salary<1500000){
                   taxNewRegime=salary*0.25;
                   message="25% of salary to be paid under new regime for range 12.5 lakh to 15 lakh.Amount to be paid is "+taxNewRegime;
               }else{
                   taxNewRegime=salary*0.3;
                   message="30% of salary to be paid under new regime for above 15 lakh.Amount to be paid is "+taxNewRegime;
               }
               break;
       }
       return message;
    }


}
