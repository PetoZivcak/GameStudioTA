package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Occupation;
import sk.Tsystems.GameStudio.entity.Player;

import java.util.List;

public interface PlayerService {
    public List<Player> getPlayersByUserName(String uName);
    public void addPlayer(Player player);
    public Player getPlayerByUserNameAndFullName(String userName, String fullName);
}
