import commands.Command;
import commands.Invoker;
import commands.Receiver;
import commands.impl.CreateNewUserCommand;
import commands.impl.LoginCommand;
import model.Network;

import java.util.Scanner;

/**
 * Created by user on 9/23/2017.
 */
public class Starter {
    public static void main(String[] args) {

        Receiver receiver = new Receiver(new Network());
        Command createNewUserCommand = new CreateNewUserCommand(receiver);
        Command loginCommand = new LoginCommand(receiver);
        Command createPublicMessageCommand = new CreateNewUserCommand(receiver);

        Invoker invoker = new Invoker();
        boolean exit = false;

         try (Scanner scanner = new Scanner(System.in)) {
             do {

                 System.out.println("Main Menu:");
                 System.out.println("0 - Create a user");
                 System.out.println("1 - Login");
                 System.out.println("2 - Create a public message");
                 System.out.println("666 - exit");
                 int i = scanner.nextInt();

                 switch (i) {
                     case 0: invoker.setCommand(createNewUserCommand);invoker.run();break;
                     case 1: invoker.setCommand(loginCommand);invoker.run();break;
                     case 2: invoker.setCommand(createPublicMessageCommand);invoker.run();break;
                     case 666: exit = true; break;
                     default: throw new IllegalArgumentException("");
                 }
             }while (!exit);
         }

    }
}
