package services;

import commands.Command;
import commands.Invoker;
import dao.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Menu {
    private final Invoker invoker;
    private final Command createNewUserCommand;
    private final Command loginCommand;
    private final Command createPublicMessageCommand ;
    private final Command createPrivateMessageCommand;
    private final Command addFriendCommand;
    private final Command showAllPublicMessagesCommand ;
    private final Command showAllPrivateMessagesCommand;
    private final Command showFriendListCommand;
    private final Command ratingMessagesCommand;
    private final Command findUserCommand;
    private final DataDao dataDao;

    @Autowired
    public Menu(Invoker invoker, Command createNewUserCommand, Command loginCommand, Command createPublicMessageCommand,
                Command createPrivateMessageCommand, Command showAllPublicMessagesCommand,
                Command showAllPrivateMessagesCommand, Command showFriendListCommand, Command addFriendCommand,
                Command ratingMessagesCommand, Command findUserCommand, DataDao dataDao) {
        this.invoker = invoker;
        this.createNewUserCommand = createNewUserCommand;
        this.loginCommand = loginCommand;
        this.createPublicMessageCommand = createPublicMessageCommand;
        this.createPrivateMessageCommand = createPrivateMessageCommand;
        this.showAllPublicMessagesCommand = showAllPublicMessagesCommand;
        this.showAllPrivateMessagesCommand = showAllPrivateMessagesCommand;
        this.showFriendListCommand = showFriendListCommand;
        this.addFriendCommand = addFriendCommand;
        this.ratingMessagesCommand = ratingMessagesCommand;
        this.findUserCommand = findUserCommand;
        this.dataDao = dataDao;
    }

    public void showMenu() {
        boolean exit = false;
        dataDao.createTableIfNotExist();
        dataDao.getUsersFromDB();

        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            do {

                System.out.println("Main Menu:");
                System.out.println("0 - Create a user");
                System.out.println("1 - Login");
                System.out.println("2 - Create a public message");
                System.out.println("3 - Create a private message");
                System.out.println("4 - Show all public message");
                System.out.println("5 - Show all private message");
                System.out.println("6 - Show Friend List");
                System.out.println("7 - Add a friend");
                System.out.println("8 - Rating Messages");
                System.out.println("9 - Find a User");
                System.out.println("666 - exit");

                int i = scanner.nextInt();
                switch (i) {
                    case 0: invoker.setCommand(createNewUserCommand);invoker.run();break;
                    case 1: invoker.setCommand(loginCommand);invoker.run();break;
                    case 2: invoker.setCommand(createPublicMessageCommand);invoker.run();break;
                    case 3: invoker.setCommand(createPrivateMessageCommand);invoker.run();break;
                    case 4: invoker.setCommand(showAllPublicMessagesCommand);invoker.run();break;
                    case 5: invoker.setCommand(showAllPrivateMessagesCommand);invoker.run();break;
                    case 6: invoker.setCommand(showFriendListCommand);invoker.run();break;
                    case 7: invoker.setCommand(addFriendCommand);invoker.run();break;
                    case 8: invoker.setCommand(ratingMessagesCommand);invoker.run();break;
                    case 9: invoker.setCommand(findUserCommand);invoker.run();break;
                    case 666: exit = true; break;
                    default: throw new IllegalArgumentException("");
                }
            }while (!exit);
        }
    }
}