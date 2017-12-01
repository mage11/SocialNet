package commands.impl;

import commands.Command;
import commands.Receiver;
import dao.DataDao;
import dao.impl.DataDaoImpl;
import model.Message;
import model.Network;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAllPrivateMessagesCommand implements Command {
    private final Receiver receiver;
    private static Logger LOGGER = LogManager.getLogger();

    @Autowired
    public ShowAllPrivateMessagesCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        User user = network.getCurrentUser();
        DataDao dataDao = new DataDaoImpl(receiver);

        List<Message> messageList = dataDao.getPrivateMessagesFromDB(user.getLogin());
        for (Message i : messageList) {
            System.out.println(i.getMessage());
            System.out.println("---------------------");
        }
        LOGGER.info("Private messages showed");
    }

}