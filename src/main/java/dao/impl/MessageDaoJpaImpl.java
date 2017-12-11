package dao.impl;

import dao.MessageDao;
import dao.UserDao;
import model.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MessageDaoJpaImpl implements MessageDao {
    UserDao userDao = new UserDaoImpl();

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveMessageToDB(Message message) {
        entityManager.persist(message);
    }

    @Override
    public List<Message> getPublicMessagesFromDB(String login) {
        List<Message> messageList = entityManager.createQuery("from Message WHERE sender = :sender AND type = :type", Message.class)
                .setParameter("sender", login)
                .setParameter("type",0)
                .getResultList();
        for(Message i : messageList){
            i.setSender(userDao.findUserInDB(i.getSender()).getLogin());
            i.setRecipient("0");
        }
        return messageList;
    }

    @Override
    public List<Message> getPrivateMessagesFromDB(String login) {
        List<Message> messageList = entityManager.createQuery("from Message WHERE sender = :sender AND type = :type", Message.class)
                .setParameter("sender", login)
                .setParameter("type",1)
                .getResultList();
        for(Message i : messageList){
            i.setSender(userDao.findUserInDB(i.getSender()).getLogin());
            i.setRecipient(userDao.findUserInDB(i.getRecipient()).getLogin());
        }
        return messageList;
    }
}
