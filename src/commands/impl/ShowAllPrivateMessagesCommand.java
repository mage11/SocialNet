package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Message;
import model.Network;
import model.User;


public class ShowAllPrivateMessagesCommand implements Command {
    private final Receiver receiver;

    public ShowAllPrivateMessagesCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        User user = network.getCurrentUser();

        for (int i = 0; i < user.getMessageList().size(); i++) {
            Message message = user.getMessageList().get(i);
            if (message.getType() == 1) {
                System.out.println(message.getMessage());
                System.out.println("---------------------");
            }

        }

    }
}