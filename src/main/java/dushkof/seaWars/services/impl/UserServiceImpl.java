package dushkof.seaWars.services.impl;

import dushkof.seaWars.controllers.HelloController;
import dushkof.seaWars.objects.Role;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.RoleRepo;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

@Service
@Configuration
@EnableAutoConfiguration
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private GameService gameService;

    @Resource
    UserRepo userRepo;
    @Resource
    RoleRepo roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Override
    public boolean saveUser(User user) {
        User userFromDB = userRepo.findByName(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        Role role_user = new Role(1L, "ROLE_USER");
        roleRepository.save(role_user);
        user.setName(user.getUsername());
        user.setRoles(Collections.singleton(role_user));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }
}
