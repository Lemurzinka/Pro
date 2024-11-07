package academy.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private  UsersList usrList = UsersList.getInstance();



    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException {
byte [] buf = Utils.requestBodyToArray(request);
String bufStr = new String(buf, StandardCharsets.UTF_8);

User user = User.fromJSON(bufStr);
if (user != null) {
    OutputStream os  = response.getOutputStream();
    if (usrList.addUser(user)) {
        os.write("User registered".getBytes(StandardCharsets.UTF_8));
    }else {
        os.write("User already registered".getBytes(StandardCharsets.UTF_8));
    }

}else {
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
}

}}
