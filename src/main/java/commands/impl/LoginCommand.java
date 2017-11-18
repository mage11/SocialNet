package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Receiver;
import main.java.model.Network;
import main.java.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class LoginCommand implements Command {
    private final Receiver receiver;

    @Autowired
    public LoginCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in,"UTF-8");

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
