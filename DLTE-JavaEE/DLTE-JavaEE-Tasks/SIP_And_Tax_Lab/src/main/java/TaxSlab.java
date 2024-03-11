import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/tax/*")
public class TaxSlab extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String regime=req.getParameter("regime");
        double salary=0;
        double taxOldRegime=0,taxNewRegime=0;
        String message="";
        salary=Integer.parseInt(req.getParameter("salary"));
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
        Gson gson=new Gson();
        String messageFinal=gson.toJson(message);
        resp.getWriter().println(messageFinal);
    }
}
