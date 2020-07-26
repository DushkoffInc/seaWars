package dushkof.seaWars.controllers;


import dushkof.seaWars.services.GameService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("game")
public class GameController {

    @Resource
    private GameService gameService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String createGame(@RequestParam(value = "name") final String name) {
        return gameService.createGame(name);
    }
}
