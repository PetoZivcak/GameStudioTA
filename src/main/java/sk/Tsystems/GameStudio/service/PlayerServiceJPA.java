package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Occupation;
import sk.Tsystems.GameStudio.entity.Player;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PlayerServiceJPA implements PlayerService{
@PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Player> getPlayersByUserName(String uName) {

        try {

            return entityManager.createQuery("select p from Player p where p.username=:uname order by p.username desc")
                    .setParameter("uname",uName)
                    .getResultList();

        }catch (NoResultException e){
            System.out.println("Ziadny hrac s user name "+uName+" nebol najdeny");

        }
       return null;
    }

    @Override
    public void addPlayer(Player player) {
        Player playerToWrite=null;
        try {

           playerToWrite=(Player)  entityManager.createQuery("select p from Player p where p.username=:uname")
                    .setParameter("uname",player.getUsername())
                    .getSingleResult();
           playerToWrite.setSelfevaluation(player.getSelfevaluation());
//           playerToWrite.setUsername(player.getUsername());
//           playerToWrite.setFullname(player.getFullname());
//           playerToWrite.setCountry(player.getCountry());
//           playerToWrite.setOccupation(player.getOccupation());
           if (playerToWrite!=null){
               System.out.println("Hrac s user name "+player.getUsername()+" uz existuje");
           }

        }catch (NoResultException e){
            playerToWrite=new Player(player.getUsername(),player.getFullname(), player.getSelfevaluation(),player.getCountry(),player.getOccupation());
           entityManager.persist(playerToWrite);

        }

    }

    @Override
    public Player getPlayerByUserNameAndFullName(String userName, String fullName) {

        try {

            return(Player) entityManager.createQuery("select p from Player p where p.username=:uname and p.fullname=:fullname")
                    .setParameter("uname",userName)
                    .setParameter("fullname",fullName)
                    .getSingleResult();

        }catch (NoResultException e){
            System.out.println("Ziadny hrac s user name "+userName+" nebol najdeny");

        }
        return null;
    }
    }



