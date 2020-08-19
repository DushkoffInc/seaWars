package dushkof.seaWars.controllers;


import dushkof.seaWars.form.GameForm;
import dushkof.seaWars.form.UserForm;
import dushkof.seaWars.objects.Field;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);


    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Value("${error.create.message}")
    private String errorCreateMessage;

    @Resource
    UserRepo userRepo;

    @Resource
    GameRepo gameRepo;

    @Resource
    GameService gameService;

    @Resource
    UserService userService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = {"/userList"}, method = RequestMethod.GET)
    public String personList(Model model) {
        List<User> persons = userRepo.findAll();

        model.addAttribute("persons", persons);

        return "personList";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "addPerson";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String savePerson(Model model,
                             @ModelAttribute("userForm") UserForm userForm) {

        String name = userForm.getName();
        String password = userForm.getPassword();
        String nickname = userForm.getNickname();
        String USER_CREATE = userService.userCreate(name, password, nickname);
        try {
            if (USER_CREATE.equals("OK")) {
                return "redirect:/lobby/?name=" + name;
            } if (USER_CREATE.equals("NOK < 4") || USER_CREATE.equals("NOK only num")) {
                LOGGER.info("User " + name + " not created");
                model.addAttribute("errorCreateMessage", errorCreateMessage);
                return "addPerson";
            } else model.addAttribute("errorMessage", errorMessage);
                return "addPerson";

        } catch (Exception e) {
            LOGGER.info("User " + name + " not created");
            LOGGER.info(e.getMessage());
            model.addAttribute("errorMessage", errorMessage);
            return "addPerson";
        }
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String tryLogin(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "login";
    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(Model model,
                        @ModelAttribute("userForm") UserForm userForm) {
        String name = userForm.getName();
        String password = userForm.getPassword();
        if ( userService.checkUserPassword(name, password) == "OK" ) {
            LOGGER.info("User " + name + "has logged in");
            return "redirect:/lobby/?name=" + name;
        } else {
            LOGGER.info("User " + name + " has not logged in");
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = {"/lobby"}, method = RequestMethod.GET)
    public String freeGameList(Model model, @RequestParam(value = "name") final String name) {
        List<Game> games = gameService.foundNewGames();
        model.addAttribute("games", games);
        model.addAttribute("name", name);
        User user = userRepo.findByName(name);
        model.addAttribute("user", user);
        model.addAttribute("lobbyLink", "/room/?name=" + name);
        return "lobby";
    }

    @RequestMapping(value = {"/createRoom"}, method = RequestMethod.GET)
    public String roomWithPlayers(Model model, @RequestParam(value = "name") final String name) throws IOException {
        gameService.createGame(name);
        return "redirect:/room/?name=" + name + "&game=" + foundMyGame(name).getId();
    }

    @RequestMapping(value = {"/joinRoom"}, method = RequestMethod.GET)
    public String joinRoom(Model model, @RequestParam(value = "name") final String name, @RequestParam(value = "game") final Long id ) {
        gameService.connectSecondUser(id, name);
        return "redirect:/room/?name=" + name + "&game=" + id;
    }

    @RequestMapping(value = {"/room"}, method = RequestMethod.GET)
    public String updateRoom(Model model, @RequestParam(value = "name") final String name, @RequestParam(value = "game") final Long id) {
        User user = userRepo.findByName(name);
        Game game = gameRepo.findGameById(id);
        model.addAttribute("user", user);
        model.addAttribute("game", game);
        return "room";
    }


    private Game foundMyGame(String name) {
        User user = userRepo.findByName(name);
        List<Game> games = gameRepo.findByUserHost(user);

        for (Game game : games) {
            if ( BooleanUtils.isNotTrue(game.getFinished()) ) {
                return game;
            }
        }
        return null;
    }

}
