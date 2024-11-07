package academy.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns =  {"/chatroom"})
public class ChatRoomServlet extends HttpServlet {
    private static final UsersList usrList = UsersList.getInstance();

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String login = req.getParameter("login");

        User user = usrList.containsLogin(login);
        System.out.println(user);
        if(user != null) {
            OutputStream out = resp.getOutputStream();
            byte[] chats = user.getStringChats().getBytes(StandardCharsets.UTF_8);
            out.write(chats);
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


}
