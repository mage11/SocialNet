package dao.impl;

import dao.BaseDao;
import dao.MessageDao;
import model.Message;
import org.springframework.cache.annotation.Cacheable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Service
public class MessageDaoImpl extends BaseDao implements MessageDao {

    @Override
    @Cacheable(value ="message")
    public void saveMessageToDB(Message message) {
        String sql = "INSERT INTO message (message, sender, recipient, type) VALUES (?,?,?,?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, message.getMessage());
            statement.setString(2, message.getSender());
            statement.setString(3, message.getRecipient());
            statement.setInt(4, message.getType());
            statement.execute();
            connection.commit();
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    @Cacheable(value ="message")
    public List<Message> getPublicMessagesFromDB(String login){
        String sql = "SELECT * FROM messages WHERE type = (?) AND sender = (?)";
        ResultSet resultSet;
        List<Message> messages = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 0);
            statement.setString(2, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                messages.add(new Message(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4)));
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    @Cacheable(value ="message")
    public List<Message> getPrivateMessagesFromDB(String login){
        String sql = "SELECT * FROM messages WHERE type = (?) AND login = (?)";
        ResultSet resultSet;
        List<Message> messages = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 1);
            statement.setString(2, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                messages.add(new Message(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4)));
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void createTableIfNotExist() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            String createusertable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT(11) PRIMARY KEY," +
                    "name VARCHAR(30)," +
                    "surname VARCHAR(30)," +
                    "sex VARCHAR(7)," +
                    "login VARCHAR(30) UNIQUE," +
                    "password VARCHAR(30))" +
                    "birthday VARCHAR(10)";

            statement.execute(createusertable);

            String createmessagetable = "CREATE TABLE IF NOT EXISTS messages (" +
                    "message VARCHAR(1500)," +
                    "sender VARCHAR(30)," +
                    "recipient VARCHAR(30)," +
                    "type INT(4)";
            statement.execute(createmessagetable);

            String createfriendstable = "CREATE TABLE IF NOT EXISTS friends (" +
                    "loginOne VARCHAR(30," +
                    "loginTwo VARCHAR(30),";
            statement.execute(createfriendstable);

        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }
}
