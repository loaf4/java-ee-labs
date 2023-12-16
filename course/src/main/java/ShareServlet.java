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

public class ShareServlet extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("user");
            String list = (request.getParameter("list"));
            String shareEmail = (request.getParameter("share"));

            if (shareEmail != null) {
                Map<String, User> users = (HashMap<String, User>) getServletContext().getAttribute("users");
                shareEmail = shareEmail.trim();
                if (!users.containsKey(shareEmail) || users.get(shareEmail).getLists().containsKey(list)) {
                    response.sendRedirect(request.getContextPath());
                    return;
                }
                TaskList shareTaskList = user.getLists().get(list);
                users.get(shareEmail).getLists().put(list, shareTaskList);
                DataIO.writeData((HashMap<String, User>)getServletContext().getAttribute("users"));
            }
        }

        response.sendRedirect(request.getContextPath());
    }
}