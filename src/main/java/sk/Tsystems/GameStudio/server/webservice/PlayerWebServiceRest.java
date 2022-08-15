package sk.Tsystems.GameStudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.Tsystems.GameStudio.entity.Player;
import sk.Tsystems.GameStudio.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerWebServiceRest {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/{player}")
    public List<Player> getPlayers(@PathVariable String player){
        return playerService.getPlayersByUserName(player);
    }


}
