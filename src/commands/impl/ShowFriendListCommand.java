package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

import java.util.List;


public class ShowFriendListCommand implements Command {
    private final Receiver receiver;

    public ShowFriendListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        User currentUser = network.getCurrentUser();
        List<User> friendList = currentUser.getFriendList();

        int i = 1;
        for(User user : friendList ){
            System.out.println(i + ". " + user.getSurname() + " " + user.getName()  );
            i++;
        }

    }
}