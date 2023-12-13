import org.example.Task;
import org.example.TaskList;
import org.example.User;

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
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Test</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>aslkdjflkasjfdjlasf</h1>");
            out.println("</body>");
            out.println("</html>");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}