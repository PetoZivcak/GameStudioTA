package sk.Tsystems.GameStudio.minesweeper;

import sk.Tsystems.GameStudio.entity.Rating;
import sk.Tsystems.GameStudio.entity.Score;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public class PlaygroundJPA {
    @PersistenceContext
    private EntityManager entityManager;

public void play(){
    System.out.println("Opening JPS");
    String game="minesweeper";
    String user="Feri";
    int ratingValue=1;
    entityManager.persist(new Rating(game,user, ratingValue,new Date()));
}

//   public void play(){
//        System.out.println("Opening JPA playground");
//        entityManager.persist(new Score("minesweeper","PetoZivcakJPA",10,new Date()));
//        entityManager.persist(new Score("minesweeper","SimonZivcakJPA",100,new Date()));
//        String game="minesweeper";
//        List<Score> bestScores=
//                entityManager
//                        .createQuery("select sc from Score sc where sc.game=:myGame order by sc.points desc")
//                        .setParameter("myGame",game)
//                        .getResultList();
//        System.out.println(bestScores);
//        System.out.println("Closing JPA playground");
//    }
}