package academy.prog;

import java.util.*;

class Main {
	private static User user = null;
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to the Chat Client!");

		login();

		startSession();


		user.logout();
		scanner.close();
	}


	public static void login() {
		while (true) {
			System.out.println("Press: ");
			System.out.print("1 - Login ");
			System.out.print("2 - Register ");

			int choice = getChoice(2);

			if (choice == 1) {
				if(action("login").equals(Utils.LOGGED_IN)){
					return;
				}else if (choice == 2) {
					action("register");
				}
			}
		}
	}

	public static void startSession() {
		while (true) {
			System.out.println("Do you want to:");
			System.out.print("1 - Private chat ");
			System.out.print("2 - Public chat ");
			System.out.print("3 - Logout ");

			int startChat = getChoice(3);

			if (startChat == 1) {
				privateChat();
			}else if (startChat == 2) {
				publicChat();
			}else if (startChat == 3) {
				break;
			}
		}
	}




	public static int getChoice(int max) {
		int choice = 0;

		while(true){
			try{
				choice = scanner.nextInt();
				if(choice < 1 || choice > max){
					throw new InputMismatchException();
				}
				break;
			}catch (NoSuchElementException e){
				System.out.println("Invalid choice. Try again.");
				scanner.nextLine();
			}

		}
		scanner.nextLine();
		return choice;
	}

	public static String action(String command) {
		System.out.println("\t** " + command.toUpperCase() + " **");
		System.out.println("Enter your login: ");
		String login = scanner.nextLine();
		System.out.println("Enter your password: ");
		String password = scanner.nextLine();

		user = new User(login, password);
		String result = user.action(command);

		System.out.println(result);
		return result;
	}

	public static void privateChat() {
		String users = printUsers();
		if(enterChat(users)){
			return;
		}
		System.out.println("There is no user with such nickname.");
		privateChat();
	}

	public static String printUsers() {
		System.out.println("Select who do you want to write:");
		String users = Utils.getUsers(user.getLogin());
		if(users.equals("")){
			System.out.println("You are the only one registered user.");
			user.logout();
			System.exit(0);
		}else {
			System.out.println(users);
		}
		return users;

	}

	public static void publicChat() {
		System.out.println("Select opinion: ");
		System.out.println("1 - Enter chat room");
		System.out.println("2 - Create new chat room");
		System.out.println("3 - Back");

		int choice = getChoice(3);
		if (choice == 1) {
			String chatRooms = Utils.getChatRooms(user.getLogin());
			System.out.println("Select a chat room to enter: ");
			if(chatRooms.equals("")){
				System.out.println("There is no available chat room.");
				publicChat();
			}else{
				System.out.println(chatRooms);
			}if(enterChat(chatRooms)){
				return;
			}else {
				System.out.println("There is no chat room with such name.");
				publicChat();
			}
			}else if (choice ==2){
System.out.println("Select at least 2 users (input separated with coma and space) to create chat room: ");
String users = Utils.getUsers(user.getLogin());
if(user.equals("")){
	System.out.println("You are the only one registered user.");
	return;
}else if (users.split(System.lineSeparator()).length<2){
	System.out.println("There are not enough registered users");
	return;
}else {
	System.out.println(users);
}
String[] chatUsers = (scanner.nextLine() + ", " + user.getLogin()).split(", ");
System.out.println("Select a chat room to enter: ");
String name = scanner.nextLine();
ChatRoom chatRoom = new ChatRoom(name, chatUsers);
Utils.addChatRoom(chatRoom);
publicChat();

		}else {
			return;
		}
	}


public static boolean enterChat(String info){
		String[] someArr = info.split(System.lineSeparator());
		String receiver = scanner.nextLine();
		for (int i = 0; i < someArr.length; i++) {
			String temp = someArr[i];
			int delimiter = someArr[i].indexOf(" ");
			if(delimiter>0){
				temp = someArr[i].substring(0, delimiter);
			}
			if (temp.equals(receiver)) {
				user.sendMessage(receiver);
				return true;
			}
		}
		return false;
};


}
