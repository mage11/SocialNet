package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 9/30/2017.
 */
public class Network {
    private final List<User> userList = new ArrayList<>();
    private User currentUser;

    public void addUser(User user) {
        userList.add(user);
    }

    public int getNumberOfUsers() {
        return userList.size();
    }

    public List<User> getUserList() { return userList; }

    public void setCurrentUser(User currentUser) { this.currentUser = currentUser; }

    public User getCurrentUser() { return currentUser; }

}