package academy.prog;
/*
    /add - POST(json) -> list
    /get?from=x - GET(json[])
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Utils {
    private static final String URL = "http://127.0.0.1";
    private static final int PORT = 8080;
    public static final String LOGGED_IN = "Logged in!";
    public static final String NOT_REGISTERED = "Not registered!";


    public static String getURL() {
        return URL + ":" + PORT;
    }


    public static byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int r;

        do{
            r = is.read(buffer);
            if(r > 0) baos.write(buffer, 0, r);
        }while (r != 1);

        return baos.toByteArray();
    }

    public static String getUsers(String exceptUser){
        try {
            URL obj = new URL(getURL() + "/user?except=" + exceptUser);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setDoOutput(true);

            InputStream is = con.getInputStream();
            byte[] buf = Utils.responseBodyToArray(is);
            return new String(buf, StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getChatRooms (String login){
        try{
            URL obj = new URL(getURL()+ "/chatroom?login=" + login);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setDoOutput(true);

            InputStream in = con.getInputStream();
            byte[] buf = Utils.responseBodyToArray(in);
            return new String(buf, StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void addChatRoom (ChatRoom chatRoom){
        try {
            URL obj = new URL(getURL() + "/addchatroom");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            String result = chatRoom.toJSON();

            os.write(result.getBytes(StandardCharsets.UTF_8));
            con.getResponseCode();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
