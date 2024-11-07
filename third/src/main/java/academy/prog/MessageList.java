package academy.prog;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;


public class MessageList {
	private static final MessageList msgList = new MessageList();

	private final Gson gson = new Gson();
	private final List<Message> list = new LinkedList<>();
	
	public static MessageList getInstance() {
		return msgList;
	}

	public synchronized void addMessage(Message message) {
		if (!list.contains(message)) {
			list.add(message);
		}
	}

	public synchronized String toJSON (String login, int n) {
		if(n>= list.size()) {
			return null;
		}
		return gson.toJson(new JsonMessages(list, login, n));
	}

}
