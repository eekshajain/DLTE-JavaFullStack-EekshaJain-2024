package servlets.web;

import org.example.entity.Account;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("WithdrawServlet")
public class NewTransaction extends HttpServlet {

    public AccountService accountService;
    public ResourceBundle resourceBundle;
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService=new AccountService(storageTarget);
        resourceBundle=ResourceBundle.getBundle("account");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        double amount=Double.parseDouble(req.getParameter("amount"));
        double updatedBalance = accountService.callWithdraw(username, password, amount);

        if (updatedBalance >= 0) {
            // If withdrawal is successful, redirect to the dashboard with the updated balance
            resp.sendRedirect("dashboard.jsp?balance=" + updatedBalance);
        } else {
            // If withdrawal fails, redirect back to the withdrawal page with an error message
            RequestDispatcher dispatcher = req.getRequestDispatcher("withdrawamount.jsp");
            req.setAttribute("error", resourceBundle.getString("transaction.failure"));
            dispatcher.forward(req, resp);
        }

    }
}
