import org.example.Task;
import org.example.TaskList;
import org.example.User;
import org.example.io.DataIO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DeleteTaskServlet extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("user");
            String list = request.getParameter("list");
            String task = (request.getParameter("task"));

            user.getLists().get(list).removeTask(task);
            DataIO.writeData((HashMap<String, User>)getServletContext().getAttribute("users"));
        }

        response.sendRedirect(request.getContextPath());
    }
}