package sk.Tsystems.GameStudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.Tsystems.GameStudio.entity.Comment;
import sk.Tsystems.GameStudio.entity.Player;

import java.util.Arrays;
import java.util.List;

public class PlayerServiceREST implements PlayerService {
    @Value("${remote.server.api}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Player> getPlayersByUserName(String uName) {
        return Arrays.asList(restTemplate.getForEntity(url + "/player", Player[].class).getBody());
    }

    @Override
    public void addPlayer(Player player) {
        restTemplate.postForEntity(url + "/player", player, Player.class);
    }

    @Override
    public Player getPlayerByUserNameAndFullName(String userName, String fullName) {
        throw new UnsupportedOperationException("Not supported via web");
    }
}
