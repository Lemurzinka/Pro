package academy.prog;

import java.util.*;

public class UsersList {
private static final UsersList usrList = new UsersList();

private final List<User> list = new ArrayList<>();

public  static UsersList getInstance(){
    return usrList;
}


    private UsersList() {
    }

    public synchronized boolean addUser(User user) {
    if(list.contains(user)) {
        return false;
    }
    list.add(user);
    return true;
    }

    public synchronized String getUsers (String except){
    StringBuilder sb = new StringBuilder();
    for(User u : list){
        if(u.getLogin().equals(except)){
            continue;
        }
        sb.append(u.getLogin());
        if(u.isOnline()){
            sb.append(" (online)");
        }
        sb.append(System.lineSeparator());
    }
    return sb.toString();
    }

    public synchronized boolean contains(User user) {
    return list.contains(user);
    }

    public synchronized User containsLogin(String login) {
    for (int i = 0; i < list.size(); i++) {
        if(list.get(i).getLogin().equals(login)) {
            return list.get(i);
        }
    }
    return null;
    }

    public synchronized User get(User user) {
    for (User u : list) {
        if(u.equals(user)) {
            return u;
        }
    }
    return null;
    }



}