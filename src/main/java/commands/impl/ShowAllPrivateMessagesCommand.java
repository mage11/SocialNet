package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Receiver;
import main.java.dao.DataDao;
import main.java.dao.impl.DataDaoImpl;
import main.java.model.Message;
import main.java.model.Network;
import main.java.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAllPrivateMessagesCommand implements Command {
    private final Receiver receiver;

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

    }

}