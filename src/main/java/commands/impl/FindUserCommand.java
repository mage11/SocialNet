package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Receiver;
import main.java.dao.DataDao;
import main.java.dao.impl.DataDaoImpl;
import main.java.model.Network;
import main.java.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class FindUserCommand implements Command {
    private final Receiver receiver;

    @Autowired
    public FindUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in,"UTF-8");

        Network network = receiver.getNetwork();
        List<User> userList = network.getUserList();
        DataDao dataDao = new DataDaoImpl(receiver);

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