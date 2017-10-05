package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

/**
 * Created by user on 9/30/2017.
 */
public class CreateNewUserCommand implements Command {
    private final Receiver receiver;

    public CreateNewUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        System.out.println("Creating new user");
        User user = new User();
        Network network = receiver.getNetwork();
        network.addUser(user);

        System.out.println("User is added to the network, number of users in the network: " + network.getNumberOfUsers());
    }
}
