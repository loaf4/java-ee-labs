import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.example.User;
import org.example.io.DataIO;

public class LoginServlet extends HttpServlet {
    @Override
    public void init() {
        if (getServletContext().getAttribute("users") == null) {
            Map<String, User> users = DataIO.readData();
            getServletContext().setAttribute("users", users);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user;
        Map<String, User> users = (HashMap<String, User>) getServletContext().getAttribute("users");
        if ((user = checkAuthentication(users, email, password)) != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("lists", user.getLists());
            response.sendRedirect(request.getContextPath());
        } else {
            response.sendRedirect(request.getContextPath() + "/login?error-auth");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private User checkAuthentication(Map<String, User> users, String email, String password) {
        User u;
        if (users != null && (u = users.get(email)) != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}