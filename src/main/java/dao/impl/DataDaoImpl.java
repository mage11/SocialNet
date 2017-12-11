package dao.impl;

import commands.Receiver;
import dao.BaseDao;
import dao.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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




}
