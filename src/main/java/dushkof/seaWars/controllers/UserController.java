package dushkof.seaWars.controllers;

import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Resource(name = "userService")
    private UserService userService;

    @Resource
    private UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //TODO метод устарел, необходимо передлать, если решим использовать api
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam(value = "name") final String name,
                       @RequestParam(value = "password") final String password) {
        User user = new User();
        try {
            userRepo.save(user);
            LOGGER.info("User " + name + " is created");
            return "OK";
        } catch (Exception e) {
            LOGGER.info("User " + name + " not created");
            LOGGER.info(e.getMessage());
            return "NOK";
        }
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check(@RequestParam(value = "name") final String name,
                      @RequestParam(value = "password") final String password) {
        return userService.checkUserPassword(name, password);
    }
}
