package academy.prog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SendMessageServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte [] buf = Utils.requestBodyToArray(req);

        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(bufStr);

        if(msg != null) {
            msgList.addMessage(msg);
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
