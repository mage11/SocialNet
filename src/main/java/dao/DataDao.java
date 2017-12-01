package dao;

import model.Message;
import model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataDao {
    void createTableIfNotExist();
    void saveUser(User user);
    void getUsersFromDB();
    void saveMessageToDB(Message message);
    void saveFriendToFriendlistDB(String loginOne, String loginTwo);
    void updateUser (User user);
    User getUserFromDB (String login);
    User findUserInDB(String name, String surname);
    List<Message> getPublicMessagesFromDB(String login);
    List<Message> getPrivateMessagesFromDB(String login);

}