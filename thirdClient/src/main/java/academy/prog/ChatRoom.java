package academy.prog;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Objects;

public class ChatRoom {
    private String roomName;
    private String[] users;



    public ChatRoom(String roomName, String[] users) {
        this.roomName = roomName;
        this.users = users;
        Arrays.sort(users);




    }
    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getRoomName() {
        return roomName;
    }

    public String[] getUsers() {
        return users;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    @Override
public boolean equals(Object o) {

        if (o == this){
            return true;
        }

        if (!(o instanceof ChatRoom)) {
            return false;
        }

        ChatRoom cr = (ChatRoom) o;
        return Arrays.equals(this.users, cr.users);

    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "roomName='" + roomName + '\'' +
                ", users=" + Arrays.toString(users) +
                '}';
    }
}
