package sk.Tsystems.GameStudio.service;

import org.junit.jupiter.api.Test;
import sk.Tsystems.GameStudio.SpringClient;
import sk.Tsystems.GameStudio.entity.Rating;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingServiceJPATest {
    private SpringClient springClient=new SpringClient();
    @Test
    public void updateValue(){
        springClient.ratingService().reset();
        Rating ranting=new Rating("minesweeper","JokoAnna",4,new Date());
        springClient.ratingService().setRating(ranting);
        assertEquals(0, 1);

    }
}
