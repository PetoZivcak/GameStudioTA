package sk.Tsystems.GameStudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sk.Tsystems.GameStudio.entity.Rating;
import sk.Tsystems.GameStudio.entity.Score;

import java.util.Arrays;
import java.util.List;

public class RatingServiceRestClient implements RatingService {

    @Value("${remote.server.api}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void setRating(Rating rating) {
        restTemplate.postForEntity(url + "/rating", rating, Rating.class);
    }

    @Override
    public int getAverageRating(String game) {
        ResponseEntity<Integer> response =  restTemplate.getForEntity(url + "/rating/average/"+game, Integer.class);
        return response.getBody();
    }

    @Override
    public int getRating(String game, String username) {
                 throw new UnsupportedOperationException("Not supported via web");
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web");
    }




}
