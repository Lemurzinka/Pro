package academy.prog;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet(urlPatterns = {"/addchatroom"})
public class AddChatRoomServlet extends HttpServlet {
private static final UsersList usrList = UsersList.getInstance();


@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    byte[] buf = Utils.requestBodyToArray(req);
    String strBuf = new String(buf, StandardCharsets.UTF_8);

    ChatRoom chatRoom = ChatRoom.fromJSON(strBuf);
    System.out.println("received: " + chatRoom);

    if (chatRoom != null) {
        for (String login: chatRoom.getUsers()){
            User user = usrList.containsLogin(login);
            usrList.get(user).addChatRoom(chatRoom);
            System.out.println("YES");
        }

    }else {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
}
