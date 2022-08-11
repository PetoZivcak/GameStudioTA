package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Rating;
import org.junit.jupiter.api.Test;
import sk.Tsystems.GameStudio.service.RatingService;
import sk.Tsystems.GameStudio.service.RatingServiceJDBC;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingServiceTest {
    //  private ScoreService scoreService=new ScoreServiceJDBC();
    private RatingService ratingService = new RatingServiceJDBC();

    @Test
    public void testReset() {
        ratingService.setRating(new Rating("sk/Tsystems/GameStudio", "Jano", 4, new Date()));
        ratingService.reset();
        assertEquals(0, ratingService.getAverageRating("sk/Tsystems/GameStudio"));
    }

    @Test
    public void testAddRating() {
        ratingService.reset();
        var date = new Date();
        ratingService.setRating(new Rating("sk/Tsystems/GameStudio", "Jano", 5, date));
        ratingService.setRating(new Rating("sk/Tsystems/GameStudio", "Jano", 4, date));
        var rating = ratingService.getRating("sk/Tsystems/GameStudio", "Jano");
        assertEquals(4, rating);
    }

    @Test
    public void testAverageRating() {
        ratingService.reset();
        var date = new Date();
        ratingService.setRating(new Rating("sk/Tsystems/GameStudio", "Jano", 5, date));
        ratingService.setRating(new Rating("sk/Tsystems/GameStudio", "Ondrej", 4, date));
        var rating = ratingService.getAverageRating("sk/Tsystems/GameStudio");
        assertEquals(5, rating);

    }


}
