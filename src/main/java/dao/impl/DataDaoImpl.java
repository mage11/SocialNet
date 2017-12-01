package dao.impl;

import commands.Receiver;
import dao.BaseDao;
import dao.DataDao;
import model.Message;
import model.Network;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataDaoImpl extends BaseDao implements DataDao {
    private final Receiver receiver;

    @Autowired
    public DataDaoImpl(Receiver receiver) {
        this.receiver = receiver;
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

    @Override
    @Cacheable(value ="user")
    public void saveUser(User user){
        String sql = "INSERT INTO users (id, name, surname, sex, login, password, birthday) VALUES (?,?,?,?,?,?,?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setLong(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getSex());
            statement.setString(5, user.getLogin());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getBirthday());
            statement.execute();
            connection.commit();
        } catch(SQLException e){
                throw new RuntimeException();
        }
    }

    @Override
    @Cacheable(value ="user")
    public void getUsersFromDB(){
        Network network = receiver.getNetwork();
        String sql = "SELECT * FROM users";
        ResultSet resultSet;
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                network.addUser(new User(resultSet.getLong(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getString(6),
                        resultSet.getString(7)));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

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
    @Cacheable(value ="user")
    public User findUserInDB(String name, String surname){
        String sql = "SELECT * FROM users WHERE name = (?) AND surname = (?)";
        ResultSet resultSet;
        User user = new User();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getLong(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getString(6),
                        resultSet.getString(7));
            }
            if (user.getName() == null) {
                System.out.println("Error");
                return null;
            }
            else { return user; }
        } catch (SQLException e) {
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
    @Cacheable(value ="user")
    public void saveFriendToFriendlistDB(String loginOne, String loginTwo){
        String sql = "INSERT INTO friends (loginOne, loginTwo) VALUES (?,?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, loginOne);
            statement.setString(2, loginTwo);
            statement.execute();
            connection.commit();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    @Cacheable(value ="user")
    public void updateUser (User user) {
        String sql = "UPDATE user SET name = ?, password = ? WHERE login = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getLogin());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Cacheable(value ="user")
    public User getUserFromDB(String login) {
        String sql = "SELECT login,name,password FROM user WHERE login = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");

                User user = new User();
                user.setName(name);
                user.setLogin(login);
                user.setPassword(password);

                return user;
            }

            throw new RuntimeException("The user is not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
