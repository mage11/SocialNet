/*
package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.UserService;

import java.util.Scanner;

@Service
public class EditUserCommand implements Command {
    private final Receiver receiver;
    private static Logger LOGGER = LogManager.getLogger();

    @Autowired
    public EditUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Autowired
    private UserService userService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in,"UTF-8");
        Network network = receiver.getNetwork();

        System.out.println("Enter user login: ");
        String login = scanner.nextLine();
        User user = userService.getUser(login);
        System.out.println("Enter a new name: ");
        String newName = scanner.nextLine();
        user.setName(newName);

        userService.update(user);
        LOGGER.info("User "+ user.getLogin() + " is updated");

    }
}
*/
