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
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserService userService;

    @Resource
    private UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam(value = "name") final String name,
                       @RequestParam(value = "password") final String password,
                         @RequestParam(value = "nickname") final String nickname) {
       return userService.userCreate(name, password, nickname);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check(@RequestParam(value = "name") final String name,
                      @RequestParam(value = "password") final String password) {
        return userService.checkUserPassword(name, password);
    }
}
