package commands.impl;


import commands.Command;
import commands.Receiver;
import dao.DataDao;
import dao.impl.DataDaoImpl;
import model.Network;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CreateNewUserCommand implements Command {
    private final Receiver receiver;
    private static Logger LOGGER = LogManager.getLogger();

    @Autowired
    public CreateNewUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        System.out.println("Creating new user");
        User user = new User();
        Network network = receiver.getNetwork();

        Scanner scanner = new Scanner(System.in,"UTF-8");
        System.out.println("Login: ");
        user.setLogin(scanner.nextLine());
        System.out.println("Password: ");
        user.setPassword(scanner.nextLine());
        System.out.println("Name: ");
        user.setName(scanner.nextLine());
        System.out.println("Surname: ");
        user.setSurname(scanner.nextLine());
        System.out.println("Birthday: ");
        user.setBirthday(scanner.nextLine());

        DataDao dataDao = new DataDaoImpl(receiver);
        dataDao.saveUser(user);
        network.addUser(user);
        LOGGER.info("User "+ user.getLogin() + " is created");
    }
}
