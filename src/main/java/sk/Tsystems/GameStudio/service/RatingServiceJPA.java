package sk.Tsystems.GameStudio.service;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.data.jpa.repository.Query;
import sk.Tsystems.GameStudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService{
@PersistenceContext
EntityManager entityManager;
    @Override
    public void setRating(Rating rating) {


           // entityManager.persist(rating);

        }



    @Override
    public int getAverageRating(String game) {

      return (int)(double) entityManager
              .createQuery("SELECT round(avg(r.rating),0) FROM Rating r where r.game=:myGame")
              .setParameter("myGame",game)
              .getSingleResult()
        ;
    }

    @Override
    public int getRating(String game, String username) {


        return 0;
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM rating");

    }
}