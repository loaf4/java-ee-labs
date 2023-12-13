import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PhoneBookServlet extends HttpServlet {
    PhoneBookModel pb = new PhoneBookModel();

    @Override
    public void init(ServletConfig config) {
        try {
            pb.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/l13/phonebook/add")) {
            pb.add(request.getParameter("name"), request.getParameter("phone"));
            pb.saveFile();
        }
        if (uri.equals("/l13/phonebook/delete-user")) {
            pb.deleteUser(request.getParameter("name"));
            pb.saveFile();
        }
        if (uri.equals("/l13/phonebook/delete-phone")) {
            pb.deletePhone(request.getParameter("name"), request.getParameter("phone"));
            pb.saveFile();
        }
        response.sendRedirect(request.getContextPath() + "/phonebook");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("notes", pb.getList());
        request.getRequestDispatcher("/Main.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        try {
            pb.saveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

