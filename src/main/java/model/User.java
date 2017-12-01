package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 9/30/2017.
 */
public class User {
   // private static long userCounter=0;
    private long id;
    private String login;
    private String name;
    private String surname;
    private String sex;
    private String birthday;
    private String password;

    private List<User> friendList = new ArrayList<>();
    private List<Message> messageList = new ArrayList<>();

    public String getLogin(){ return login;}
    public String getName() { return name;}
    public String getSurname() { return surname;}
    public String getPassword() { return password; }
    public String getBirthday() { return birthday; }
    public String getSex() {
        return sex;
    }
    public long getId() {
        return id;
    }

    public void setBirthday(String birthday) {
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
    public void setSex(String sex) {
        this.sex = sex;
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

    public User(){}
    public User(long id, String name, String surname, String sex, String login, String password, String birthday) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
        this.password = password;
    }
    public User(String login, String name, String password){
        this.login = login;
        this.name = name;
        this.password = password;
    }
}
