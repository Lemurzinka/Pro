package academy.prog;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private  final UsersList usersList = UsersList.getInstance();
    private final List<Message> list = new ArrayList<>();


    public JsonMessages(List<Message> sourceList, String login, int fromIndex) {
        User user =  usersList.containsLogin(login);


        for (int i = fromIndex; i < sourceList.size(); i++){

            String receiver = sourceList.get(i).getTo();
            if (receiver.equals(login)){
                list.add(sourceList.get(i));
            }else {
                List<ChatRoom> chatlist = user.getUserChats();
                for (int j = 0; j < chatlist.size(); j++){
                    if (receiver.equals(chatlist.get(j).getRoomName())){
                        list.add(sourceList.get(i));
                    }
                }
            }
        }
    }
}
