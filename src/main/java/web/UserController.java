package web;

import dao.MessageDao;
import dao.UserDao;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class UserController {
    private static Logger LOGGER = LogManager.getLogger();
    private final UserDao userDao;
    private final MessageDao messageDao;

    @Autowired
    public UserController(UserDao userDao, MessageDao messageDao) {
        this.userDao = userDao;
        this.messageDao = messageDao;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String startRegistration(Model model) {
        model.addAttribute(new User());
        return "startRegistration";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String finishRegistration(User user, Model model) {
        LOGGER.info("Got s new user: {}", user);
        userDao.saveUser(user);
        model.addAttribute("login", user.getLogin());
        return "redirect:/user/{username}";
    }

    @RequestMapping("/{username}")
    public String profile(@PathVariable String login, Model model) {
        User user = userDao.getUserFromDB(login);
        LOGGER.info("Finded user: {}",user.toString());
        model.addAttribute(user);
        return "profile";
    }

}