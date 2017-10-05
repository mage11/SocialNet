package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Login: ");
        user.setLogin(scanner.nextLine());
        System.out.println("Password: ");
        user.setPassword(scanner.nextLine());
        //need date here<<<<<<<<
        System.out.println("Name: ");
        user.setName(scanner.nextLine());
        System.out.println("Surname: ");
        user.setSurname(scanner.nextLine());

        network.addUser(user);

        System.out.println("User is added to the network, number of users in the network: " + network.getNumberOfUsers());
    }
}
