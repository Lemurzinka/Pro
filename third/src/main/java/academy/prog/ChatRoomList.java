package academy.prog;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomList {
    private static final ChatRoomList crList = new ChatRoomList();
    private final List<ChatRoom> list = new ArrayList<>();

    public static ChatRoomList getInstance() {
        return crList;
    }


    private ChatRoomList() {}

    public synchronized boolean addChatRoom(ChatRoom room) {
        if (list.contains(room)) {
            return false;
        }
        list.add(room);
        return true;
    }
}
