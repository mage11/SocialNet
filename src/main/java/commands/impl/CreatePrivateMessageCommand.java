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
import java.util.Scanner;

@Service
public class CreatePrivateMessageCommand implements Command {
    private final Receiver receiver;
    private static Logger LOGGER = LogManager.getLogger();

    @Autowired
    public CreatePrivateMessageCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in,"UTF-8");
        Network network = receiver.getNetwork();
        Message message = new Message();
        DataDao dataDao = new DataDaoImpl(receiver);

        System.out.println("Write a recipient login: "); //may be ID
        String recipient = scanner.nextLine();
        message.setRecipient(recipient);

        System.out.println("Write a private message: ");
        message.setMessage(scanner.nextLine());
        message.setType((byte) 1);
        message.setSender(network.getCurrentUser().getLogin());

        List<User> userList = network.getUserList();

        for (User user : userList) {
            if(user.getLogin().equals(recipient)) {
                user.addMessage(message);
                LOGGER.info("Private Message "+ message.getMessage() + " is added for recipient");
            }
        }

        network.getCurrentUser().addMessage(message);
        LOGGER.info("Message "+ message.getMessage() + " is added for sender");
        dataDao.saveMessageToDB(message);


    }
}
