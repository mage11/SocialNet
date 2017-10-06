package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Message;
import model.Network;

import java.util.Scanner;


public class CreatePublicMessageCommand implements Command {
    private final Receiver receiver;

    public CreatePublicMessageCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Network network = receiver.getNetwork();
        Message message = new Message();

        System.out.println("Write a public message: ");
        message.setMessage(scanner.nextLine());
        message.setType((byte) 0);
        message.setSender(network.getCurrentUser());
        message.setRecipient("0");

        network.getCurrentUser().addMessage(message);


    }
}
