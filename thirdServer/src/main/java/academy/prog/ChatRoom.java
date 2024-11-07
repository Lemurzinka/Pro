package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatRoom {
    private String roomName;
    private String[] users;

    public ChatRoom(String roomName, String[] users) {
        this.roomName = roomName;
        this.users = users;
        Arrays.sort(users);
    }

    public static ChatRoom fromJSON(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, ChatRoom.class);
    }

    public String toJSON() {
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
        if (o == this) {
            return true;
        }

        if (!(o instanceof ChatRoom)) {
            return false;
        }
        ChatRoom chatRoom = (ChatRoom) o;
        return Arrays.equals(this.users, chatRoom.getUsers());
    }
        @Override
                public String toString() {
            return roomName + " " + Arrays.toString(users);
        }
    }

