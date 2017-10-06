package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Message;
import model.Network;
import model.User;

import java.util.Scanner;


public class ShowAllPublicMessagesCommand implements Command {
    private final Receiver receiver;

    public ShowAllPublicMessagesCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Network network = receiver.getNetwork();
        User user = network.getCurrentUser();

        for (int i = 0; i < user.getMessageList().size(); i++) {
            Message message = user.getMessageList().get(i);
            if (message.getType() == 0) {
                System.out.println(message.getMessage());
                System.out.println("---------------------");
            }

        }

    }
}