package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

import java.util.List;
import java.util.Scanner;


public class LoginCommand implements Command {
    private final Receiver receiver;

    public LoginCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        Network network = receiver.getNetwork();
        List<User> userList = network.getUserList();

        for (User user : userList) {
            if(user.getLogin().equals(login)) {
                if(user.getPassword().equals(password)){
                    network.setCurrentUser(user);
                    return;
                }
            }

        }
        System.out.println("Error");
    }
}
