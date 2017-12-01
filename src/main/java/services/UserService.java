package services;

import model.User;

public interface UserService {
    boolean update(User validUser);
    User getUser(String login);
}
