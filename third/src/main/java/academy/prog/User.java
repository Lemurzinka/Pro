package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String login;
    private String password;
    private boolean online;
    private List<ChatRoom> userChats = new ArrayList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String toJSON (){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJSON(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, User.class);
    }

    public void addChatRoom(ChatRoom chatRoom) {
        if(!userChats.contains(chatRoom)) {
            userChats.add(chatRoom);
        }
    }

    public String getStringChats(){
        StringBuilder chats = new StringBuilder();
        for (ChatRoom chatRoom : userChats) {
            chats.append(chatRoom);
            chats.append(System.lineSeparator());
        }
        return chats.toString();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isOnline() {
        return online;
    }

    public List<ChatRoom> getUserChats() {
        return userChats;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setUserChats(List<ChatRoom> userChats) {
        this.userChats = userChats;
    }


    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", online=" + online +
                '}';
    }

    @Override
    public boolean equals(Object o) {
if (o == this) return true;
if (!(o instanceof User)) return false;
User user = (User) o;
return this.login.equals(user.login);
 }


}
