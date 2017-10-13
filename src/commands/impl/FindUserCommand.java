package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

import java.util.List;
import java.util.Scanner;


public class FindUserCommand implements Command {
    private final Receiver receiver;

    public FindUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        Network network = receiver.getNetwork();
        List<User> userList = network.getUserList();

        System.out.println("Write Name: ");
        String name = scanner.nextLine();
        System.out.println("Write Surname: ");
        String surname = scanner.nextLine();

        for(User user : userList) {
            if(user.getName().equals(name) &&  user.getSurname().equals(surname)) {
                System.out.println("Name: " + name);
                System.out.println("Surname: " + surname);
                System.out.println("Birthday: " + user.getBirthday());
            }
        }

    }
}