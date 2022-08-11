package sk.Tsystems.GameStudio.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.Tsystems.GameStudio.SpringClient;
import sk.Tsystems.GameStudio.entity.Rating;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringClient.class)
public class RatingServiceJPATest {
    @Autowired
    RatingService ratingService;

    @Test
    public void check_value_in_database_after_uptading_if_there_is_new_value_or_not() {
        ratingService.reset();
        Rating rating = new Rating("minesweeper", "Peto", 2, new Date());
        Rating rating2 = new Rating("minesweeper", "Peto", 5, new Date());
        ratingService.setRating(rating);
        ratingService.setRating(rating2);
        assertEquals(5, ratingService.getRating("minesweeper", "Peto"));


    }

    @Test
    public void check_average_rating_after_adding_three_new_rows_in_database() {
        ratingService.reset();
        Rating rating3 = new Rating("minesweeper", "Peto", 2, new Date());
        Rating rating4 = new Rating("minesweeper", "Jano", 2, new Date());
        Rating rating5 = new Rating("minesweeper", "Ondrej", 5, new Date());
        Rating rating6 = new Rating("stones", "Ondrej", 5, new Date());
        ratingService.setRating(rating3);
        ratingService.setRating(rating4);
        ratingService.setRating(rating5);
        ratingService.setRating(rating6);

        assertEquals(3, ratingService.getAverageRating("minesweeper"));


    }
}
