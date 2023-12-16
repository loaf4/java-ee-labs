import org.example.Task;
import org.example.TaskList;
import org.example.User;
import org.example.io.DataIO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    @Override
    public void init() {
        if (getServletContext().getAttribute("users") == null) {
            Map<String, User> users = DataIO.readData();
            getServletContext().setAttribute("users", users);
        }
    }
}