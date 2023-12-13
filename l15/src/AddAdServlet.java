import org.example.AdModel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AddAdServlet extends HttpServlet {
    @Override
    public void init() {
        ArrayList<AdModel> ads = new ArrayList<>();
        getServletContext().setAttribute("ads", ads);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            String username = (String) session.getAttribute("username");
            String adTitle = request.getParameter("adTitle");
            String adText = request.getParameter("adText");

            if (adTitle != null && adText != null) {
                AdModel newAd = new AdModel(adTitle, adText, username);
                ArrayList<AdModel> ads = (ArrayList<AdModel>) getServletContext().getAttribute("ads");
                ads.add(newAd);
            }
        }

        response.sendRedirect(request.getContextPath());
    }
}
