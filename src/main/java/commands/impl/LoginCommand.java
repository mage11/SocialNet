package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class LoginCommand implements Command {
    private final Receiver receiver;
    private static Logger LOGGER = LogManager.getLogger();

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
                    LOGGER.info("Login successful");
                    return;
                }
            }

        }
        LOGGER.info("Error for Login");
    }
}
