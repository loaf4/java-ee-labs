import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.example.User;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user;
        if ((user = checkAuthentication((HashMap<String, User>) getServletContext().getAttribute("users"), email, password)) != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            getServletContext().setAttribute("lists", user.getLists());
            response.sendRedirect("/course");
        } else {
            response.sendRedirect(request.getContextPath() + "/login?error-auth");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private User checkAuthentication(Map<String, User> users, String email, String password) {
        if (users.containsKey(email)) {
            User u = users.get(email);
            if (u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}