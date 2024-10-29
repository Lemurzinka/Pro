package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

// Req -> (S -> S) -> jsp

public class LoginServlet extends HttpServlet {

    ArrayList<User> users = new ArrayList<>();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String age = request.getParameter("age");
        String season = request.getParameter("season");
        String gadget = request.getParameter("gadget");

       User user = new User(name, lastname, age, season, gadget);

           users.add(user);

           request.getSession(true).setAttribute("users", users);



        response.sendRedirect("response.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
