
package dushkof.seaWars.controllers.pages;

import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/lobby")
public class LobbyController {

    @Resource
    private UserRepo userRepo;

    @Resource
    private GameService gameService;

    @GetMapping
    public String getLobby(Model model, @RequestParam(value = "name") final String name) {
        User currentUser = userRepo.findByName(name);
        model.addAttribute("user", currentUser);

        List<Game> games = gameService.foundNewGames();
        model.addAttribute("games", games);
        model.addAttribute("gamesSize", games.size());
        return "lobby";
    }
}
