import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/binding/")
public class BindingAccess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Context context = new InitialContext();
            String myString= (String) context.lookup("java:/MyFirstBind");
            Integer myInteger = (Integer) context.lookup("java:/MyFirstInteger");
            Double myDouble= (Double) context.lookup("java:/MyFirstDoubleBind");
            resp.getWriter().println(myString);
            resp.getWriter().println(myInteger);
            resp.getWriter().println(myDouble);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
