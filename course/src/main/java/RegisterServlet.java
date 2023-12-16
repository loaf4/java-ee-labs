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

public class RegisterServlet extends HttpServlet {
    @Override
    public void init() {
        if (getServletContext().getAttribute("users") == null) {
            Map<String, User> users = DataIO.readData();
            getServletContext().setAttribute("users", users);
        }
    }

    @Override
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user;
        Map<String, User> users = (HashMap<String, User>) getServletContext().getAttribute("users");
        if (users != null
                && email != null
                && password != null
                && (user = checkExistingUser(users, email, password)) == null) {
            email = email.trim();
            password = password.trim();

            user = new User(email, password);
            users.put(email, user);
            DataIO.writeData(users);

            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("lists", user.getLists());
            response.sendRedirect(request.getContextPath());
//            request.getRequestDispatcher("/main.jsp").forward(request, response);
        } else {
            if (users == null) {
                response.sendRedirect(request.getContextPath() + "/login?users-null");
            } else {
                response.sendRedirect(request.getContextPath() + "/login?error-reg");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private User checkExistingUser(Map<String, User> users, String email, String password) {
        User u;
        if (users != null && (u = users.get(email)) != null) {
            return u;
        }
        return null;
    }
}