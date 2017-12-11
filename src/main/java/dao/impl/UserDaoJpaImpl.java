package dao.impl;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoJpaImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void saveFriendToFriendlistDB(String loginOne, String loginTwo) {

    }

    @Override
    public List<User> getUsersFromDB() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserFromDB(String login) {
        return entityManager.find(User.class, login);
    }

    @Override
    public User findUserInDB(String login) {
        return entityManager.find(User.class, login);
    }
}