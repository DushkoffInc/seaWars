package dushkof.seaWars.controllers;

import dushkof.seaWars.objects.Role;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("hello")
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "gameService")
    private GameService gameService;

    @GetMapping
    public String list() {
        LOGGER.error("test error");
        LOGGER.info("test info");
        LOGGER.debug("test debug");
        LOGGER.warn("test warn");
        LOGGER.trace("test trace");
        return userService.sayHi();
    }

    @GetMapping("/newRole")
    public String createRole() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return "ok";
    }
}
