package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MessageThread implements Runnable {
    private final Gson gson;
    private int n;
    private String login;

    public MessageThread(String login) {
        this.login = login;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                URL url = new URL(Utils.getURL() + "/get?login=" + login + "&from=" +n);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                try{
                    byte[] buffer = Utils.responseBodyToArray(in);
                    String strBuffer = new String(buffer, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuffer, JsonMessages.class);
                    if(list != null) {
                        for (Message m : list.getList()){
                            System.out.println(m);
                            n++;
                        }
                    }
                }finally {
                    in.close();
                }
                Thread.sleep(500);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

