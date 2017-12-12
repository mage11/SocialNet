/*
package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Message;
import model.Network;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.RatingService;

import java.util.List;

@Service
public class RatingMessagesCommand implements Command {
    private final Receiver receiver;
    private static Logger LOGGER = LogManager.getLogger();

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
        LOGGER.info("Rating Messages");

    }
}
*/
