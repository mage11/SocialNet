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
import java.util.Scanner;

@Service
public class CreatePrivateMessageCommand implements Command {
    private final Receiver receiver;

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
                }
            }

        network.getCurrentUser().addMessage(message);
        dataDao.saveMessageToDB(message);


    }
}
