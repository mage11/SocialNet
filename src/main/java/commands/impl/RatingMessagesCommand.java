package main.java.commands.impl;


import main.java.commands.Command;
import main.java.commands.Receiver;
import main.java.model.Message;
import main.java.model.Network;
import main.java.model.User;
import main.java.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingMessagesCommand implements Command {
    private final Receiver receiver;

    @Autowired
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

    }
}
