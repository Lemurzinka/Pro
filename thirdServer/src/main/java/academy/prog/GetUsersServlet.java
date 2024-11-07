package academy.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetUsersServlet extends HttpServlet {

    private static final UsersList usrList = UsersList.getInstance();

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String exceptUser = req.getParameter("except");
        OutputStream os = resp.getOutputStream();

        byte[] buf = usrList.getUsers(exceptUser).getBytes(StandardCharsets.UTF_8);
        os.write(buf);
    }
}
