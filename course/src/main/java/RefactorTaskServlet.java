import org.example.Task;
import org.example.TaskList;
import org.example.User;
import org.example.io.DataIO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RefactorTaskServlet extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("user");
            String list = (request.getParameter("list"));
            String olderTask = (request.getParameter("older-task"));
            String task = (request.getParameter("task"));

            if (task != null) {
                task = task.trim();
                if (task.equals(olderTask) || user.getLists().get(list).getTasks().containsKey(task)) {
                    response.sendRedirect(request.getContextPath());
                    return;
                }
                Task newTask = user.getLists().get(list).getTasks().get(olderTask);
                newTask.setText(task);
                user.getLists().get(list).getTasks().remove(olderTask);
                user.getLists().get(list).getTasks().put(task, newTask);
                DataIO.writeData((HashMap<String, User>)getServletContext().getAttribute("users"));
            }
        }

        response.sendRedirect(request.getContextPath());
    }
}