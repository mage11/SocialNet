package main.java.commands.impl;


import main.java.commands.Command;
import main.java.commands.Receiver;
import main.java.dao.DataDao;
import main.java.dao.impl.DataDaoImpl;
import main.java.model.Network;
import main.java.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CreateNewUserCommand implements Command {
    private final Receiver receiver;

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
        System.out.println("User is added to network.");

        System.out.println("User is added to the network, number of users in the network: " + network.getNumberOfUsers());
    }
}
