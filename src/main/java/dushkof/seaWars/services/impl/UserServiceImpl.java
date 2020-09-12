package dushkof.seaWars.services.impl;

import dushkof.seaWars.controllers.HelloController;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

@Configuration
@EnableAutoConfiguration
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private GameService gameService;

    @Resource
    UserRepo userRepo;

    @Override
    public String sayHi() {
        return "hi";
    }

    @Override
    public String checkUserPassword(String name, String password) {
        try {
            if (userRepo.findByName(name).getPassword().equals(password)) {
                LOGGER.info("Password for user " + name + " is true");
                return "OK";
            } else
                LOGGER.info("Incorrect password for " + name);
            return "NOK";
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return "NOK";
        }
    }

    @Override
    public String userCreate(String name, String password, String nickname){
        boolean onlyNumbers = name.matches("^[0-9]+$");
        if (onlyNumbers) {
            return "NOK only num";
        }
        if (password.length() < 4) {
            return "NOK < 4";
        }
        boolean testUsers = name.contains("test");
        if (testUsers) {
            return "NOK";
        }
        User user = new User(name, password, nickname);
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

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setGameService(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    public GameService getGameService() {
        return gameService;
    }
}
