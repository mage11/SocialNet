package dao;

import model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);
    void updateUser (User user);
    void saveFriendToFriendlistDB(String loginOne, String loginTwo);
    List<User> getUsersFromDB();
    User getUserFromDB (String login);
    User findUserInDB(String login);
}
