package commands.impl;

import commands.Command;
import commands.Receiver;
import dao.DataDao;
import dao.impl.DataDaoImpl;
import model.Message;
import model.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CreatePublicMessageCommand implements Command {
    private final Receiver receiver;

    @Autowired
    public CreatePublicMessageCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in,"UTF-8");
        Network network = receiver.getNetwork();
        Message message = new Message();
        DataDao dataDao = new DataDaoImpl(receiver);

        System.out.println("Write a public message: ");
        message.setMessage(scanner.nextLine());
        message.setType((byte) 0);
        message.setSender(network.getCurrentUser().getLogin());
        message.setRecipient("0");

        network.getCurrentUser().addMessage(message);
        dataDao.saveMessageToDB(message);


    }
}
