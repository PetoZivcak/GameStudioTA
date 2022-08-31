package sk.Tsystems.GameStudio.service;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.data.jpa.repository.Query;
import sk.Tsystems.GameStudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void setRating(Rating rating) {
        Rating ratingWrite = null;
        try {

            ratingWrite = (Rating) entityManager.createQuery("select r from Rating r where r.game= :game and r.username= :user ")

                    .setParameter("game", rating.getGame())
                    .setParameter("user", rating.getUsername())
                    .getSingleResult();
            ratingWrite.setRated_on(new Date());
            ratingWrite.setRating(rating.getRating());

        } catch (NoResultException e) {
            ratingWrite = new Rating(rating.getGame(), rating.getUsername(), rating.getRating(), new Date());
//    ratingWrite.setGame(rating.getGame());
//    ratingWrite.setUsername(rating.getUsername());
//    ratingWrite.setRating(rating.getRating());
//    ratingWrite.setRated_on(new Date());

            entityManager.persist(ratingWrite);
        }


    }


    @Override
    public int getAverageRating(String game) {

        return (int) (double) entityManager
                .createQuery("SELECT round(avg(r.rating),0) FROM Rating r where r.game=:myGame")
                .setParameter("myGame", game)
                .getSingleResult()
                ;
    }

    @Override
    public int getRating(String game, String username) {


        return (Integer) entityManager.createQuery("SELECT r.rating FROM Rating r WHERE r.game=:game and r.username=:username")
                .setParameter("game", game)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM rating")
                .executeUpdate();


    }
}