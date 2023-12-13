import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.example.User;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user;
        Map<String, User> users = (HashMap<String, User>) getServletContext().getAttribute("users");
        if ((user = checkExistingUser(users, email, password)) == null
                && email != null
                && password != null) {
            email = email.trim();
            password = password.trim();
            user = new User(email, password);
            users.put(email, user);
            response.sendRedirect("/login?email" + email + "&password" + password);
        } else {
            response.sendRedirect(request.getContextPath() + "/login?error-reg");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private User checkExistingUser(Map<String, User> users, String email, String password) {
        if (users.containsKey(email)) {
            User u = users.get(email);
            if (u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}