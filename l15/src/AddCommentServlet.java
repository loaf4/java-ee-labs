import org.example.AdModel;
import org.example.AdComment;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AddCommentServlet extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            int id = Integer.parseInt(request.getParameter("adId"));
            String username = (String) session.getAttribute("username");
            String commentText = request.getParameter("commentText");

            if (commentText != null) {
                AdComment comment = new AdComment(commentText, username);
                ArrayList<AdModel> ads = (ArrayList<AdModel>) getServletContext().getAttribute("ads");
                for (AdModel ad : ads) {
                    if (ad.getId() == id) {
                        ad.getComments().add(comment);
                        break;
                    }
                }
            }
        }

        response.sendRedirect(request.getContextPath());
    }
}
