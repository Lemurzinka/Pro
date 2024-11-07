package academy.prog;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.net.URL;

public class User {
    private String login;
    private String password;
    private boolean online;
    private List<ChatRoom> userChats = new ArrayList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void sendMessage(String receiver){
        Scanner scanner = new Scanner(System.in);
        System.out.print("You entered!, !q to quit: ");
        try{
            Thread th = new Thread(new MessageThread(this.login));
th.setDaemon(true);
th.start();

System.out.print("Enter your message: ");

while (true){
    String message = scanner.nextLine();
    if (message.equals("!q")) break;

    Message m = new Message(this.login, receiver, message);
    int res = m.send(Utils.getURL() + "/send");

    if (res != 200){
        System.out.println("Something went wrong!");
        return;
    }
}
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String action(String action){
        try{
            URL obj = new URL(Utils.getURL() + "/" + action);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            String json = this.toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));

            InputStream is = con.getInputStream();
            byte[] b = Utils.responseBodyToArray(is);
            String result = new String(b, StandardCharsets.UTF_8);

            if (result.equals(Utils.LOGGED_IN)){
                this.online = true;
            }
            return result;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public void logout(){
        try {
            URL obj = new URL(Utils.getURL() + "/logout?login=" + this.login);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.getResponseCode();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Logged out!");
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
