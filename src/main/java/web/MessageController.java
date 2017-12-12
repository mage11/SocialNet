package web;

import dao.MessageDao;
import dao.UserDao;
import model.Message;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class MessageController {

    private final UserDao userDao;
    private final MessageDao messageDao;
    private static Logger LOGGER = LogManager.getLogger();

    @Autowired
    public MessageController(UserDao userDao, MessageDao messageDao) {
        this.userDao = userDao;
        this.messageDao = messageDao;
    }

    @RequestMapping(path = "/id={id}/messages",method = RequestMethod.GET)
    public String showAllMessages(Model model, @PathVariable String login){
        List<Message> publicMessageList = messageDao.getPublicMessagesFromDB(login);
        LOGGER.info("Public Message List: ", publicMessageList.toString());
        List<Message> privateMessageList = messageDao.getPrivateMessagesFromDB(login);
        LOGGER.info("Private Message List: ", privateMessageList.toString());
        model.addAttribute("login", "login = ");
        model.addAttribute("publicMessageList", publicMessageList);
        model.addAttribute("privateMessageList", privateMessageList);
        return "messages";
    }

    @RequestMapping(path = "/id={id}/sendmessages", method = RequestMethod.GET)
    public String sendMessagesPage(Model model, @PathVariable String id) {
        model.addAttribute(new Message());
        List<User> userList = userDao.getUsersFromDB();
        model.addAttribute("userList", userList);
        model.addAttribute("id", "id = ");
        model.addAttribute("space", " ");
        return "sendmessages";
    }





}