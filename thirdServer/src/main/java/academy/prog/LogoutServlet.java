package academy.prog;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    private static  final UsersList usersList = UsersList.getInstance();

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        User user = usersList.containsLogin(login);

        if (user != null) {
            usersList.get(user).setOnline(false);
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
