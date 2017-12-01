package commands.impl;

import commands.Command;
import commands.Receiver;
import dao.DataDao;
import dao.impl.DataDaoImpl;
import model.Network;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class AddFriendCommand implements Command {
    private final Receiver receiver;

    @Autowired
    public AddFriendCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        List<User> userList = network.getUserList();
        DataDao dataDao = new DataDaoImpl(receiver);

        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.println("Write a friend name: ");
        String name = scanner.nextLine();
        System.out.println("Write a friend surname: ");
        String surname = scanner.nextLine();

        for (User friend : userList) {
            if(friend.getName().equals(name)) {
                if(friend.getSurname().equals(surname)) {
                    friend.addFriend(network.getCurrentUser());
                    network.getCurrentUser().addFriend(friend);
                    dataDao.saveFriendToFriendlistDB(network.getCurrentUser().getLogin(),
                            friend.getLogin());
                    return;
                }
            }
        }
        System.out.println("User does not exist");

    }
}