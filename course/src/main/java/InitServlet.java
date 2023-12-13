import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.example.User;
import org.example.io.DataIO;

public class InitServlet extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    public void init() {
        Map<String, User> users = new HashMap<>();
        DataIO.readData(users);
        getServletContext().setAttribute("users", users);
    }
}