package dao;

import model.Message;

import java.util.List;

public interface MessageDao {
    void saveMessageToDB(Message message);
    List<Message> getPublicMessagesFromDB(String login);
    List<Message> getPrivateMessagesFromDB(String login);

}
