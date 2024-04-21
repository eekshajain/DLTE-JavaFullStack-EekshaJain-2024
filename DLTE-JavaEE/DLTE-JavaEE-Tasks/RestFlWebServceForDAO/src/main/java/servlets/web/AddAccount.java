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
import java.sql.SQLException;
import java.util.ResourceBundle;

@WebServlet("CreateAccountServlet")
public class AddAccount extends HttpServlet {
    public AccountService accountService;
    public ResourceBundle resourceBundle;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService=new AccountService(storageTarget);
        resourceBundle=ResourceBundle.getBundle("account");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        long accNumber=Long.parseLong(req.getParameter("accountNumber"));
        long customerId=Long.parseLong(req.getParameter("customerId"));
        String email=req.getParameter("email");
        double balance=Double.parseDouble(req.getParameter("balance"));
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Account account=new Account(accNumber,customerId,email,name,balance,username,password);
        RequestDispatcher dispatcher=req.getRequestDispatcher("newaccount.jsp");
        accountService.callAddAccounts(account);
        req.setAttribute("info", resourceBundle.getString("account.create"));
        dispatcher.forward(req, resp);
    }
}
