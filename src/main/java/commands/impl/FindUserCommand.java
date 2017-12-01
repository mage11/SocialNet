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

import java.util.List;
import java.util.Scanner;

@Service
public class FindUserCommand implements Command {
    private final Receiver receiver;
    private static Logger LOGGER = LogManager.getLogger();

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
                LOGGER.info("User "+ user.getLogin() + " is finded");
            }
        }

    }
}