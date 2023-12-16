import org.example.SubTask;
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

public class RefactorSubTaskServlet extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("user");
            String list = (request.getParameter("list"));
            String task = (request.getParameter("task"));
            String olderSubTask = (request.getParameter("older-sub-task"));
            String subTask = (request.getParameter("sub-task"));

            if (subTask != null) {
                subTask = subTask.trim();
                if (subTask.equals(olderSubTask) || user.getLists().get(list).getTasks().get(task).getSubTasks().containsKey(subTask)) {
                    response.sendRedirect(request.getContextPath());
                    return;
                }
                SubTask newSubTask = user.getLists().get(list).getTasks().get(task).getSubTasks().get(olderSubTask);
                newSubTask.setText(subTask);
                user.getLists().get(list).getTasks().remove(olderSubTask);
                user.getLists().get(list).getTasks().get(task).getSubTasks().put(task, newSubTask);
                DataIO.writeData((HashMap<String, User>)getServletContext().getAttribute("users"));
            }
        }

        response.sendRedirect(request.getContextPath());
    }
}