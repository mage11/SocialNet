import commands.Command;
import commands.Invoker;
import commands.Receiver;
import commands.impl.CreateNewUserCommand;
import model.Network;

import java.util.Scanner;

/**
 * Created by user on 9/23/2017.
 */
public class Starter {
    public static void main(String[] args) {

        Receiver receiver = new Receiver(new Network());
        Command createNewUserCommand = new CreateNewUserCommand(receiver);

        Invoker invoker = new Invoker();

        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the command number");
            int i = scanner.nextInt();

            switch (i) {
                case 0: invoker.setCommand(createNewUserCommand); invoker.run(); break;
                default: throw new IllegalArgumentException("");
            }
        }

    }
}
