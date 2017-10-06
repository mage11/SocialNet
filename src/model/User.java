package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 9/30/2017.
 */
public class User {
    private static long userCounter=0;
    private long id;
    private String login;
    private String name;
    private String surname;
    private Date birthday;
    private String password;

    private List<User> friendList = new ArrayList<>();
    private List<Message> messageList = new ArrayList<>();

    public String getLogin(){ return login;}
    public String getName() { return name;}
    public String getSurname() { return surname;}
    public String getPassword() { return password; }
    public Date getBirthday() { return birthday; }
    public long getId() {
        return id;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Message> getMessageList() {
        return messageList;
    }
    public List<User> getFriendList() {
        return friendList;
    }

    public void addMessage(Message message) {
        this.messageList.add(message);
    }
    public void addFriend(User friend) {
        this.friendList.add(friend);
    }

    public User() {userCounter++; id = userCounter;}
    public User(long id, String login, String name, String surname, Date birthday, String password){
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.password = password;
    }


}
