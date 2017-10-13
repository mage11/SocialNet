package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Message;
import model.Network;
import model.User;
import services.RatingService;

import java.util.List;


public class RatingMessagesCommand implements Command {
    private final Receiver receiver;

    public RatingMessagesCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        User user = network.getCurrentUser();
        List<Message> userList = user.getMessageList();
        String allMessages ="";

        RatingService service = new RatingService();
        service.setMessageList(userList);
        service.setUserName(user.getName());
        service.getRatingMessages(allMessages);

        //Map<Integer, String> topWords = service.getTopWords();

    }
}
