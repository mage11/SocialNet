package services.impl;

import dao.DataDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DataDao dataDao;

    @Override
    public boolean update(User user) {
        dataDao.updateUser(user);
        return true;
    }

    @Override
    public User getUser(String login) {
        User user = dataDao.getUserFromDB(login);
        return user;
    }
}