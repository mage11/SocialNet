package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

import java.util.List;
import java.util.Scanner;


public class AddFriendCommand implements Command {
    private final Receiver receiver;

    public AddFriendCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        List<User> userList = network.getUserList();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write a friend name: ");
        String name = scanner.nextLine();
        System.out.println("Write a friend surname: ");
        String surname = scanner.nextLine();

        for (User friend : userList) {
            if(friend.getName().equals(name)) {
                if(friend.getSurname().equals(surname)) {
                    network.getCurrentUser().addFriend(friend);
                    friend.addFriend(network.getCurrentUser());
                    return;
                }
            }
        }
        System.out.println("User does not exist");

    }
}