package academy.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private final UsersList usersList = UsersList.getInstance();


    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


byte [] buf = Utils.requestBodyToArray(req);
String strBuf = new String(buf, StandardCharsets.UTF_8);

User user = User.fromJSON(strBuf);
        OutputStream os = resp.getOutputStream();


  if (usersList.contains(user)){
      if(usersList.get(user).getPassword().equals(user.getPassword())){
          usersList.get(user).setOnline(true);
          os.write("Logged in".getBytes(StandardCharsets.UTF_8));

      }else {
          os.write("Wrong password".getBytes(StandardCharsets.UTF_8));
      }
  }else {
      os.write("User not found".getBytes(StandardCharsets.UTF_8));
  }
   }
}
