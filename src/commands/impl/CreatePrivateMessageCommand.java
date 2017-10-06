package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Message;
import model.Network;
import model.User;

import java.util.List;
import java.util.Scanner;


public class CreatePrivateMessageCommand implements Command {
    private final Receiver receiver;

    public CreatePrivateMessageCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Network network = receiver.getNetwork();
        Message message = new Message();

        System.out.println("Write a recipient login: "); //may be ID
        String recipient = scanner.nextLine();
        message.setRecipient(recipient);

        System.out.println("Write a private message: ");
        message.setMessage(scanner.nextLine());
        message.setType((byte) 1);
        message.setSender(network.getCurrentUser());

        List<User> userList = network.getUserList();

        for (User user : userList) {
            if(user.getLogin().equals(recipient)) {
                user.addMessage(message);
                }
            }

        network.getCurrentUser().addMessage(message);


    }
}
